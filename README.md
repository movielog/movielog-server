# MovieLog [![Build Status](https://app.travis-ci.com/movielog/movielog-server.svg?branch=main)](https://app.travis-ci.com/movielog/movielog-server)

<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/183861381-564dba9d-440a-4a42-b2fa-1cbb8b1fd7b0.png">
</div>

안녕하세요. `MovieLog`의 `Back-end`페이지입니다!
- 영화에 관한 정보를 얻을 수 있습니다.
- Spring Security와 JWT를 이용하여 사용자를 인증하고 식별합니다.
- 로그인 한 사용자는 영화에 관한 리뷰를 작성 할 수 있습니다.
- 로그인을 하지 않으면 작성된 리뷰만 볼 수 있습니다.


# 메인 페이지
<img src="https://user-images.githubusercontent.com/13285280/183862325-1054a624-4ff3-498d-8ba1-8906ba9e6257.png">


# 시스템 구성
<div align="center">
<img width="800" img src="https://user-images.githubusercontent.com/13285280/190374482-29d14be8-2058-456c-b2a7-7fef25002ab3.png">
</div>


# ERD
<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/184073301-efcd484a-63f7-4cb0-8563-985aafe25074.png">
</div>


# 기술 스택
- Server
  - Java 
  - SpringBoot
  - JPA
  - Spring Data JPA
  - JWT
  - Amazon EC2
  - Junit5
- Database
  - H2(local)
  - Maria DB
  - Amazon RDS
- CI
  - Travis CI
  - CodeDeploy
  - Amazon S3


# 프로젝트 구조

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



# 작업 내용
- **MOVIE API**
  - 메인 화면 (전체 조회)
    - GET `/movie`
  - 영화 상세 (개별 조회)
    - GET `/movie/{movieId}`
- **USER API**
  - [Spring Security & JWT 적용](#spring-security와-jwt)
  - 회원 가입
    - POST `/join`
  - 로그인
    - [POST `/login`](#/login-(로그인-api))
  - 닉네임 수정 [*(USER ROLE 필요 페이지)*](#서비스-접근-권한-관리)
    - GET `/user/me` (front - 수정 페이지)
    - [POST `/user/me`](#/user/me-(닉네임-수정-api))
  - 회원 탈퇴
    - DELETE `/user`
- **REVIEW API**
  - 리뷰 작성
    - GET `/review/write/{movieId}` (front - 작성 페이지)
    - POST `/review/wirte/{movieId}`
  - 전체 리뷰 목록 조회
    - GET `/review`
  - 나의 리뷰 목록 조회 *(USER ROLE 필요 페이지)*
    - GET `/my/review`
    - [findAllByUser (review)](#findAllByUser-(review))로 리뷰 목록 조회
- **ORDERS API**
  - 영화 주문
    - GET `/order/{movieId}` (front - 구매 페이지)
    - POST `/order/{movieId}`
  - 나의 주문 목록 전체 조회 *(USER ROLE 필요 페이지)*
    - GET `/my/order`
    - [findAllByUser (order)](#findAllByUser-(order))로 구매 목록 조회
  - 나의 주문 목록 개별 조회 *(USER ROLE 필요 페이지)*
    - GET `/my/order/{orderId}`
- AWS EC2, RDS와 연동하여 배포 작업 진행
  - 프리티어의 [EC2 메모리 부족 현상](#ec2-메모리-부족-현상) 이슈
- [도메인 연결](#도메인-연결)
- [HTTPS 적용](#HTTPS-적용)

---

# 주요 개발 내용

## Spring Security와 JWT
### Spring Security는 Spring 기반 어플리케이션의 사용자 인증, 인가 및 보안을 담당하는 스프링 하위 프레임워크
>Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications. <br> 
Spring Security is a framework that focuses on providing both `authentication and authorization` to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements.
> [참고 페이지](https://spring.io/projects/spring-security)


*인증* 절차를 거친 후에 *인가* 절차를 진행함
- 인증(Authentication) : 사용자가 누구인지, 해당 사용자가 본인이 맞는지 확인하는 절차
- 인가(Authorization) : 인증된 사용자가 요청한 자원에 [접근 가능한지 결정](#서비스-접근-권한-관리)하는 절차

프로젝트에 Spring Security를 사용하기 위해 의존성 추가
``` java
implementation 'org.springframework.boot:spring-boot-starter-security'
```


### JWT(Json Web Token) : Json 객체를 통해 안전하게 정보를 전송할 수 있는 웹 표준
*토큰 기반의 인증 시스템에서 널리 사용되는 인증 방식 중 하나* <br>
> JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties.
> [참고 페이지](https://jwt.io/)


#### JWT 구조
- . 를 구분자로 나누어지는 세가지 문자열의 조합
- Header, Payload, Signature로 이루어져 있음
  - Header : 토큰의 타입, 암호와 알고리즘으로 구성
  - Payload : 실제로 회원을 구분 할 수 있는 정보(Claim) 포함 (민감 정보[ex. 비밀번호 등]는 포함하면 안됨)
  - Signature : Header에서 정의한 알고리즘 방식 활용, Secret Key를 포함하여 암호화

<div align="center">
<img width="600" alt="image" src="https://user-images.githubusercontent.com/13285280/192286106-88d61ca1-52cf-451a-865d-7bb90d0eaab2.png">
</div>

**사용자 식별 과정**
1. 사용자가 인증되면 서버에서는 JWT를 생성하여 클라이언트에 전달
2. 클라이언트에서는 그 JWT를 받아서 가지고 있다가, 서버에 API 요청시 JWT를 HTTP Header에 담아서 보냄
3. 서버는 클라이언트가 전달한 JWT를 이용하여 사용자 식별

이 과정을 거치면 아래와 같은 JWT가 생성
> *ex)*
> eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoaUBnbWFpbC5jb20iLCJyb2xlcyI6W10sImlhdCI6MTY2NDI1Njc4OSwiZXhwIjoxNjY0MjU4NTg5fQ.ySIGyzKAJscUnYRtbAepRUotaohWtNpl2ja-REYMOmI


- JWT는 Stateless하게 *서버에서 사용자를 식별할 수 있는 수단*을 제공함
- 또한 JWT는 클라이언트에 저장되기 때문에 서버의 부담을 덜 수 있음 


### 실제 개발 과정
JWT 의존성 추가 설정
``` java
implementation 'io.jsonwebtoken:jjwt:0.9.1'
```


### /login (로그인 API)
서버에 로그인 하여 JWT를 생성하는 과정

- 로그인 요청이 오면 Controller를 통해 UserService.java의 login 실행
  ``` java
  // Userservice.java에서 JWT 리턴
  return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
  ```
- 클라이언트에 입력한 id & password가 일치하면 **JWT 생성**
  ``` java
  public String createToken(String userPk, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(userPk); // Payload에 저장되는 정보 단위
    claims.put("roles", roles);
    Date now = new Date(); 
    
    return Jwts.builder()
            .setClaims(claims) 
            .setIssuedAt(now) // 토큰 발행 시간 설정
            .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 만료 시간 설정
            .signWith(SignatureAlgorithm.HS256, secretKey) // Signature에 사용할 암호화 알고리즘, secret 값 설정
            .compact();
  }
  ```


- **Postman으로 테스트**
  - `POST: /login` 요청을 보내면 토큰을 생성해 반환하며
  - 이 토큰을 프론트엔드에 저장하게 됨
    <div align="center">
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/13285280/192440992-bda97b2b-dcb4-40ed-85f6-dcba69c00a4d.png">
    </div>


### /user/me (닉네임 수정 API)
API 요청시 프론트엔드에서는 Header에 JWT를 담아 보냄
- 닉네임 수정과 같은 권한이 필요한 요청이 올 때마다, 서버는 Header의 X-AUTH-TOKEN에 담긴 JWT을 가져옴
  ``` java
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader("X-AUTH-TOKEN");
  }
  ```
- 가져온 JWT에서 사용자 정보 추출
  ``` java
  public String getUserPk(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }
  ```
- JWT에서 추출한 사용자 정보(email)를 `loadUserByUsername(username)` 함수의 [findByEmail](#findByEmail)을 통해 **실제 DB에서 유저 정보**를 가져옴
  - username : 이 프로젝트에서는 email에 해당
- DB에서 가져온 사용자 정보를 UserDetails 객체에 담아 return 함
  - 이때 사용자 정보에서 USER ROLE 권한을 확인하여 리소스 제공
- **Postman으로 테스트**
  - `POST: /user/me` 요청시 Header의 X-AUTH-TOKEN에 (로그인 시 생성한) JWT를 함께 보냄
    - Body에 수정할 닉네임 설정 
  - JWT을 통해 USER ROLE을 확인하여 닉네임 수정 성공
    <div align="center">
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/13285280/192442123-45cafa2a-64fd-4543-a178-17f13333cb55.png">
    <img width="600" alt="image" src="https://user-images.githubusercontent.com/13285280/192442456-d71fe35d-f8e0-4d7d-a300-a8cfcf2ddd94.png">
    </div>


### 서비스 접근 권한 관리
필요한 곳에만 접근 권한을 부여하여 보안을 적용
- URL 별 권한 설정 가능
- WebSecurityConfigure.java의 configure()에서 설정
  - `hasRole("USER")`를 통해 *USER, MY 서비스*에 USER ROLE을 가진 사용자만 접근 허용
    - *USER, MY 서비스* : 닉네임 수정, 회원 탈퇴, 나의 리뷰 목록, 나의 주문 목록
  - `permitAll()`을 통해 이외의 서비스는 USER가 아니어도 접근 가능하게 함
    ``` java
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
      httpSecurity  
              ...
              .and()
              .authorizeRequests()
              .antMatchers("/api/user/**").hasRole("USER")
              .antMatchers("/api/my/**").hasRole("USER")
              .antMatchers("/api/**").permitAll()
              .antMatchers("/profile").permitAll()
              .and()
              ...
    }
    ```
        

## Spring Data JPA
### Spring Data JPA는 스프링에서 JPA를 편리하게 사용할 수 있도록 지원 <br>
특히 데이터 접근 계층 개발 시 반복 작성해 온 기본 CRUD 기능도 모두 제공 <br>
- CRUD 처리를 위한 공통 인터페이스([JpaRepository 상속](#jparepository-상속)) 제공
- 인터페이스만 작성하면 동적으로 구현체를 생성해서 주입해줌
- 따라서 인터페이스만 작성해도 개발을 완료 할 수 있도록 지원

[공통 메소드](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html) 에는 `count, delete, deleteAll, findById..` 등이 있으며,
공통 메소드가 아닌 경우에도 네이밍 룰을 이용하여 [메소드를 작성 하여 쿼리 실행](#메소드-이름으로-쿼리-실행) 가능


### JpaRepository 상속
- CRUD 처리를 위한 *공통 인터페이스*
- 이 인터페이스를 상속받은 인터페이스는 해당 엔티티에 대해 CRUD를 바로 사용 할 수 있음
- 실제 구현체는 SimpleJpaRepository이며 이 클래스가 구현
``` java
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
```


### 메소드 이름으로 쿼리 실행
- JPA가 제공하는 쿼리 메소드 기능 중 하나
- 메소드의 이름을 설정하여 필요한 쿼리를 실행할 수 있음
- 인터페이스에 메소드 선언만 하면 바로 사용 가능


#### findByEmail
- *적용1)* 
  - 이메일(email)로 회원(user)을 조회하기 위해 UserRepository에 별도의 메소드 생성
    - 앞서 언급한 `loadUserByUsername(username)`함수에서 username으로 계정 정보 조회 시 사용
    ``` java
    public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByEmail(String email);
    }
    ```
  - query = `select u from User u where u.email = ?1`


#### findAllByUser (review)
- *적용2)* [MY - 내 리뷰]의 유저별 리뷰 목록
  - 특정 유저(user)가 작성한 리뷰(review)를 조회하기 ReviewRepository에 별도의 메소드 생성
    ``` java
    public interface ReviewRepository extends JpaRepository<Review, Long> {
      List<Review> findAllByUser(User user);
    }
    ```
  - query = `select r from Review r where r.user = :user`


#### findAllByUser (order)
- *적용3)* [MY - 구매내역]의 유저별 주문 목록
  - 특정 유저(user)가 생성한 주문(order)를 조회하기 OrderRepository에 별도의 메소드 생성
    ``` java
    public interface OrderRepository extends JpaRepository<Order, Long> {
      List<Order> findAllByUser(User user);
    }
    ```
  - query = `select o from Orders o where o.user = :order`


- 참고 : [Spring Data JPA 공식 문서](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)


## EC2 메모리 부족 현상
- 문제 확인
  - **배포 시도** : 배포하기 위해 EC2에서 -jar 명령어 실행시 갑자기 정지함
  - 네트워크 연결이 끊기고 아무것도 할 수 없으며, 다시 빌드 해도 동일한 현상 발생
  - AWS EC2 인스턴스 모니터링(Cloud watch) 확인하니 **CPU 사용률**이 급상승하다가 하락하는 것을 확인 
    <br><img width="300" alt="image" src="https://user-images.githubusercontent.com/13285280/191682426-1d939fad-e7f6-408f-a1af-fae10253f1f2.png">
- 원인
  - 이 프로젝트에서 사용하는 t2.micro(프리티어)는 메모리가 1GB
  - 빌드 규모가 커지면 메모리가 부족하여 이러한 현상이 나타남
- 해결 방법
  - 가장 간단한 해결 방법은 인스턴스 업그레이드이지만(비용 부과) Swap 메모리를 이용한 방법을 찾음
  - 스와핑(Swapping) : 리눅스는 하드 디스크를 가상 메모리로 전환시켜 사용
    - 스왑 공간 쿠기 계산
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
4) AWS Route53에서 레코드 유형 A에 EC2 탄력적 IP 연결 
5) SSL(Secure Sockets Layer) 보안이 적용되지 않은 HTTP 도메인 연결 완료


## HTTPS 적용
### 기존 HTTP에 SSL 보안 적용
- 클라이언트와 서버가 주고 받는 모든 데이터 암호화
- 데이터 암호화를 위해 SSL을 사용함

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