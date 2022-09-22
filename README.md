# MovieLog [![Build Status](https://app.travis-ci.com/movielog/movielog-server.svg?branch=main)](https://app.travis-ci.com/movielog/movielog-server)

<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/183861381-564dba9d-440a-4a42-b2fa-1cbb8b1fd7b0.png">
</div>

안녕하세요. `MovieLog`의 `Back-end`페이지입니다!
- 영화에 관한 정보를 얻을 수 있습니다.
- 로그인 한 유저는 영화에 관한 리뷰를 작성 할 수 있습니다.


## 메인 페이지
<img src="https://user-images.githubusercontent.com/13285280/183862325-1054a624-4ff3-498d-8ba1-8906ba9e6257.png">


## 시스템 구성
<div align="center">
<img width="800" img src="https://user-images.githubusercontent.com/13285280/190374482-29d14be8-2058-456c-b2a7-7fef25002ab3.png">
</div>


## ERD
<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/184073301-efcd484a-63f7-4cb0-8563-985aafe25074.png">
</div>


## 기술 스택
- Server
  - Java 
  - SpringBoot
  - JPA
  - JWT
  - Amazon ec2
  - Junit5
- Database
  - H2(local)
  - Maria DB
  - Amazon RDS
- CI
  - Travis CI
  - CodeDeploy
  - Amazon S3


## 프로젝트 구조

- 프로젝트는 프론트엔드 1인, 백엔드 1인으로 나누어 개발했습니다. <br>
  - 프론트엔드는 react를 사용했습니다.
- 프론트엔트 프로젝트를 백엔드 프로젝트 안에 포함시켜 배포하였고, 아래 패키지 구조에서 확인 할 수 있습니다. <br>
- [프론트엔드 저장소](https://github.com/movielog/movielog-client) readme에서는 구현 동작을 확인 할 수 있습니다.

<details>
    <summary>Backend 패키지 구조 확인</summary>

```jsx
⭐️movielog-server⭐️
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── web
    │   │           └── movielog
    │   │               ├── configure
    │   │               ├── controller
    │   │               │   ├── movie
    │   │               │   ├── order
    │   │               │   ├── profile
    │   │               │   ├── review
    │   │               │   └── user
    │   │               ├── model
    │   │               │   ├── movie
    │   │               │   ├── order
    │   │               │   ├── review
    │   │               │   └── user
    │   │               ├── repository
    │   │               │   ├── movie
    │   │               │   ├── order
    │   │               │   ├── review
    │   │               │   └── user
    │   │               ├── security
    │   │               └── service
    │   ├── ⭐️movielog-client⭐️
    │   └── resources
    └── test
        ├── java
        │   └── com
        │       └── example
        │           └── movielog
        │               ├── controller
        │               ├── repository
        │               └── service
        └── resources
```

</details>


<details>
    <summary>Frontend 패키지 구조 확인</summary>

```
⭐️movielog-client⭐️
└── src
    ├── api
    ├── components
    │   ├── Header
    │   ├── Join
    │   ├── Login
    │   ├── Main
    │   ├── MovieDetail
    │   ├── MovieOrder
    │   ├── My
    │   ├── MyOrder
    │   ├── MyReview
    │   ├── OrderInfo
    │   ├── Profile
    │   ├── ReviewBoard
    │   ├── ReviewEdit
    │   └── ReviewWrite
    └── hooks
```

</details>



## 작업 내용
- MOVIE API
  - 메인 화면 (전체 조회)
    - GET `/movie`
  - 영화 상세 (개별 조회)
    - GET `/movie/{movieId}`
- USER API
  - 회원 가입
    - POST `/join`
  - 로그인
    - POST `/login`
    - [jwt 기반 api 구현](#jwt-기반-api-구현)
  - 회원 닉네임 수정
    - GET `/user/me` (front - 수정 페이지)
    - POST `/user/me`
    - [회원 권한](#회원-권한)이 있어야 접근 가능
    - [로그인 한 회원](#로그인-한-회원)의 정보 조회하여 닉네임 수정
  - 회원 탈퇴
    - DELETE `/user`
    - 회원 권한이 있어야 접근 가능
    - 로그인 한 회원의 정보 조회하여 회원 탈퇴
- REVIEW API
  - 리뷰 작성
    - GET `/review/write/{movieId}` (front - 작성 페이지)
    - POST `/review/wirte/{movieId}`
  - 전체 리뷰 목록 조회
    - GET `/review`
  - 나의 리뷰 목록 조회
    - GET `/my/review`
    - 회원 권한이 있어야 접근 가능
    - [query method](#query-method)로 조건을 만들어 쿼리 생성
- ORDERS API
  - 영화 주문
    - GET `/order/{movieId}` (front - 구매 페이지)
    - POST `/order/{movieId}`
  - 주문 목록 전체 조회
    - GET `/my/order`
    - 회원 권한이 있어야 접근 가능
    - query method로 조건을 만들어 쿼리 생성
  - 주문 목록 개별 조회
    - GET `/my/order/{orderId}`
    - 회원 권한이 있어야 접근 가능
- AWS ec2, RDS와 연동하여 배포 작업 진행
  - 프리티어의 [ec2 메모리 부족 현상](#ec2-메모리-부족-현상) 이슈
- [도메인 연결](#도메인-연결)
- [HTTPS 적용](#HTTPS-적용)

---
## jwt 기반 api 구현
### JWT(Json Web Token) : Json 객체를 통해 안전하게 정보를 전송할 수 있는 웹 표준
  - *로그인 한 회원의 토큰*을 이용하여 회원을 식별할 수 있음
    - 로그인이 성공하면 백엔드에서는 JWT를 생성하여 프론트엔드에 전달하며
    - 프론트엔드는 그 JWT를 받아서 가지고 있다가, 백엔드 API 요청시 JWT를 헤더에 담아서 보냄
  - 백엔드에 로그인 요청이 오면 오버라이드 한 `loadUserByUsername(username)` 함수를 통해 DB에서 유저 정보를 불러와 UserDetails 객체(사용자의 정보를 담는 인터페이스)를 return 함
    - 이 때 JWT에서 인증 정보 확인시 username(이 프로젝트에서는 email에 해당)을 넘김
  - 로그인 등 권한이 필요한 요청이 올 때마다 서버는 헤더에 담긴 JWT 값에서 USER ROLE 확인 후 리소스 제공
  - *Postman을 이용해 테스트*
    - 미리 가입한 user를 이용하여 POST: /login 요청을 보내면 토큰이 반환
    - 다른 리소스를 요청 할 때, headers의 key(X-AUTH-TOKEN)에 토큰을 담아 보내 권한을 확인 함
    <div align="center"><img width="600" alt="image" src="https://user-images.githubusercontent.com/13285280/191187795-f87c4db4-2256-4db7-bf57-685855dad325.png"></div>


## 회원 권한
### 필요한 곳에만 선택적으로 보안을 적용
  - WebSecurityConfigure.java의 configure()에서 설정
    - **USER, MY** 서비스에 *USER ROLE* 설정 (USER만 접근 가능)
    - 이외의 서비스는 USER가 아니어도 접근 가능
  - antMatchers("/api/user/**") : 와일드 카드 이용하여 서비스 경로 지정 
    - hasRole("USER") : USER ROLE을 가진 사용자만 접근을 허용
    - permitAll() : 모든 사용자 접근 허용
      ``` java
      @Override
      protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                ...
                .antMatchers("/api/user/**").hasRole("USER")
                .antMatchers("/api/my/**").hasRole("USER")
                .antMatchers("/api/order/**").hasRole("USER")
                .antMatchers("/api/**").permitAll()
                ...
      }
      ```


## query method
Spring Data JPA에서는 Repository 인터페이스에 네이밍 룰을 이용하여 메소드를 작성하면 원하는 쿼리 실행 가능
  
  - *적용1)* 앞서 언급한 `loadUserByUsername(username)`함수에서 username으로 계정 정보 조회 시
    - 이메일(email)로 회원(user)을 조회하기 위해 UserRepository에 별도의 메소드 생성
      ``` java
        public interface UserRepository extends JpaRepository<User, Long> {
          Optional<User> findByEmail(String email);
        }
      ```
    - query = `select u from User u where u.email = ?1`


  - *적용2)* [MY - 내 리뷰]에서 조회하는 데이터 
    - 특정 유저(user)가 작성한 리뷰(review)를 조회하기 ReviewRepository에 별도의 메소드 생성
    
      ``` java
        public interface ReviewRepository extends JpaRepository<Review, Long> {
          List<Review> findAllByUser(User user);
        }
      ```
    - query = `select r from Review r where r.user = :user`


  - *적용3)* [MY - 구매내역]에서 조회하는 데이터
    - 특정 유저(user)가 생성한 주문(order)를 조회하기 OrderRepository에 별도의 메소드 생성
      ``` java
        public interface OrderRepository extends JpaRepository<Order, Long> {
          List<Order> findAllByUser(User user);
        }
      ```
    - query = `select o from Orders o where o.user = :order`


  - 참고 : [Spring Data JPA 공식 문서](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)


## ec2 메모리 부족 현상
  - 문제 확인
    - **배포 시도** - 배포하기 위해 ec2에서 -jar 명령어 실행시 갑자기 정지함
    - 네트워크 연결이 끊기고 아무것도 할 수 없으며, 다시 빌드 해도 동일한 현상 발생
    - AWS ec2 인스턴스 모니터링(Cloud watch) 확인하니 **CPU 사용률**이 급상승하다가 하락하는 것을 확인 
      <br><img width="300" alt="image" src="https://user-images.githubusercontent.com/13285280/191682426-1d939fad-e7f6-408f-a1af-fae10253f1f2.png">
  - 원인
    - 이 프로젝트에서 사용하는 t2.micro(프리티어)는 메모리가 1GB
    - 빌드 규모가 커지면 메모리가 부족하여 이러한 현상이 나타남
  - 해결 방법
    - 가장 간단한 해결 방법은 인스턴스 업그레이드이지만(비용 부과) Swap 메모리를 이용한 방법을 찾음
    - 스와핑(Swapping) : 리눅스는 하드 디스크를 가상 메모리로 전환시켜 사용
      - 스왑 공간 계산하기
        - 물리적 RAM 크기가 2GB 이하인 경우, 권장 스왑 메모리는 RAM 용량의 2배
        - 이 프로젝트에서는 1GB*2 = 약 2GB를 스왑할 수 있음
      - 순서
        1) 루트 파일 시스템에 스왑파일을 생성한다 `sudo dd if=/dev/zero of=/mnt/swapfile bs=128M count=16`
           1) 128M * 16개 = 약 2GB
        2) 스왑 파일의 읽기, 쓰기 권한 업데이트 `sudo chmod 600 /swapfile`
        3) Linux 스왑 영역 설정 `sudo mkswap /swapfile`
        4) 스왑 공간에 스왑 파일을 추가하여 스왑 파일 즉시 사용 `sudo swap /swapfile`
        5) 성공했는지 확인 `sudo swapon -s`
        6) /etc/fstab 파일을 편집하여 부팅 시 스왑 파일 시작 `sudo vi /etc/fstab`
           1) 파일 끝에 `/swapfile swap swap defaults 0 0` 추가
           2) 저장 후 종료
        7) free로 메모리 상태 확인
    
    - swap 공간 확장! 메모리 약 3GB 사용 가능
      <div><img width="700" alt="image" src="https://user-images.githubusercontent.com/13285280/191201219-03956226-c281-476d-8800-b1d546b993b9.png"></div>
  - 참고 : [AWS 홈페이지](https://aws.amazon.com/ko/premiumsupport/knowledge-center/ec2-memory-swap-file/)


## 도메인 연결
  1) GoDaddy에서 도메인 구매 
  2) AWS Route53에 호스팅 영역 생성 
  3) 호스팅 영역이 생성되면 유형 NS의 값 4개를 GoDaddy의 네임 서버에 설정 
  4) AWS Route53에서 레코드 유형 A에 ec2 탄력적 IP 연결 
  5) SSL 보안이 적용되지 않은 HTTP 도메인 연결 완료


## HTTPS 적용
### 기존 HTTP에 SSL 보안 적용이 필요하여 추가
  1) AWS Certificate Manager 통해 ACM 인증서 발급
     - 도메인 이름을 입력하여 인증서 요청 후 레코드 검증 완료 
  2) 로드 밸런서 설정
     1) 타겟 그룹 만들기
     2) 로드 밸런서 생성 - Application Load Balancer
        1) 포트 추가할 때 타겟 그룹 적용
        2) SSL 보안 : listener에 ACM 인증서 적용
  3) Route53에서 단순 라우팅 설정
     1) 레코드 유형 A를 선택
     2) 값/트래픽 라우팅 대상에 이전에 만든 로드 밸런서 적용
     3) 레코드를 생성하면 HTTPS 연결 완료