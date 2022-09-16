# MovieLog [![Build Status](https://app.travis-ci.com/movielog/movielog-server.svg?branch=main)](https://app.travis-ci.com/movielog/movielog-server)

<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/183861381-564dba9d-440a-4a42-b2fa-1cbb8b1fd7b0.png">
</div>

안녕하세요. `MovieLog`의 `Back-end`페이지입니다!
- 영화에 관한 정보를 얻을 수 있습니다.
- 로그인 한 유저는 영화에 관한 리뷰를 작성 할 수 있습니다.


## 메인 페이지
<img src="https://user-images.githubusercontent.com/13285280/183862325-1054a624-4ff3-498d-8ba1-8906ba9e6257.png">


## 기술 스택
- Server
  - Java 
  - SpringBoot
  - JPA
  - JWT
  - Amazon EC2
- Database
  - H2(local)
  - Maria DB
  - Amazon RDS
- CI
  - Travis CI
  - CodeDeploy
  - Amazon S3

## 시스템 구성
<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/190374482-29d14be8-2058-456c-b2a7-7fef25002ab3.png" width="800">
</div>

## ERD
<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/184073301-efcd484a-63f7-4cb0-8563-985aafe25074.png">
</div>


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
    - [Jwt 기반 API 구현](#jwt) : 입력 받은 email, password가 DB와 일치하면 Jwt 토큰을 만들어 반환
  - 회원 닉네임 수정
    - GET `/user/me` - 수정 페이지(front)
    - POST `/user/me`
    - [회원 권한](#user)이 있어야 접근 가능
    - [로그인 한 회원](#login-user)의 정보 조회하여 닉네임 수정
  - 회원 탈퇴
    - DELETE `/user`
    - 회원 권한이 있어야 접근 가능
    - 로그인 한 회원의 정보 조회하여 회원 탈퇴
- REVIEW API
  - 리뷰 작성
    - GET `/review/write/{movieId}` - 작성 페이지(front)
    - POST `/review/wirte/{movieId}`
  - 전체 리뷰 목록 조회
    - GET `/review`
  - 나의 리뷰 목록 조회
    - GET `/my/review`
    - 회원 권한이 있어야 접근 가능
    - JPA method로 조건을 만들어 쿼리 생성
- ORDERS API
  - 영화 주문
    - GET `/order/{movieId}` - 구매 페이지(front)
    - POST `/order/{movieId}`
  - 주문 목록 전체 조회
    - GET `/my/order`
    - 회원 권한이 있어야 접근 가능
    - JPA method로 조건을 만들어 쿼리 생성
  - 주문 목록 개별 조회
    - GET `/my/order/{orderId}`
    - 회원 권한이 있어야 접근 가능
- AWS EC2, RDS와 연동하여 배포 작업 진행
  - 프리티어 사용 중 [메모리 부족 현상](#memory) 해결
- 도메인 구매 및 적용
  - godaddy에서 도메인 구매
  - Route53에서 도메인 등록
- HTTPS 적용
  - AWS Certificate Manager 통해 인증서 발급

---
- [Jwt 기반 API 구현](#jwt)
  - 작성중
- [USER 권한을 가져야만 접근 가능](#user)
  - WebSecurity.java에서 권한 설정하여 user, my관련 서비스는 회원(USER)만 접근 가능
 