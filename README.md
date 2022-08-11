# MovieLog [![Build Status](https://app.travis-ci.com/movielog/movielog-server.svg?branch=main)](https://app.travis-ci.com/movielog/movielog-server)

<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/183861381-564dba9d-440a-4a42-b2fa-1cbb8b1fd7b0.png">
</div>

영화 정보를 얻고 그에 관한 리뷰를 작성 할 수 있는 `MovieLog`의 `Back-end`페이지입니다!


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



## ERD
<div align="center">
<img src="https://user-images.githubusercontent.com/13285280/184073301-efcd484a-63f7-4cb0-8563-985aafe25074.png">
</div>


## 주요 기능
- USER
  - 회원 가입
  - 회원 닉네임 수정
  - 회원 탈퇴
- MOVIE
  - 전체 목록 조회
  - 개별 정보 조회 
- REVIEW
  - 영화 리뷰 작성
  - 리뷰 목록 조회
- ORDERS
  - 영화 주문





## 작업 내용
- 전체 MOVIE 목록 조회 API 구현
- 개별 MOVIE 내용 조회 API 구현
- JWT 기반 회원가입 API 구현
- JWT 기반 로그인 API 구현
- 로그인 한 회원의 정보 조회 API 구현
- 로그인 한 회원의 닉네임 수정 API 구현
- 회원 탈퇴 API 구현
- 전체 REVIEW 목록 조회 API 구현
- REVIEW 작성 API 구현
- 영화 ORDER API 구현
- 회원의 전체 ORDER 목록 조회 API 구현
- 회원의 개별 ORDER 내용 조회 API 구현
- AWS EC2, RDS와 연동하여 배포 작업
- 도메인 구매 및 적용
- HTTPS 적용