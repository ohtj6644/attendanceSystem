## 근태관리시스템 (사이드 프로젝트)
### SpringBoot / MongoDB /JAVA /RESTful API

###2023-11-21
- 프로젝트 생성
- 회원가입 구현 / 회원가입 시 uuid 객체 자동생성 및 부여 11 


###2023-11-21
- 로그인 시작 
- git 규칙 
  
    1. 새 로직 작업 시작 시 000(브랜치번호)_작업명 으로 새 브랜치 생성.
    2. 작업이 끝난 후 main에 병합 
    3. 커밋명 = [작업내용]_브랜치이름(000{브랜치번호}_작업명) 으로 통일.
  
- [mainPage_접속자권한/Login 로직 작업중]_001_login

  1. 메인페이지 반환 url:("/")  / 로그인을 한 회원만 메인페이지 접근가능. 
  2. 로그인 로직 작업중 
  

- [login_백 로직구현 완료/근태(attendance) 틀 생성 ]_001_login

  1. login 백엔드 로직 구현완료 (가입한 아이디로 로그인 시 메인페이지로 넘어감)
  2. UserRole default값 user 로 설정 / username = admin 으로 가입시 관리자권한 부여.
  3. 근태 관련 서비스,컨트롤러,레포,엔티티 생성 
  
- [Security 부분 주석 추가]_001_login

  1. MongoUserDetailsService / SecurityConfig / UserSecurityService 
  코드 설명을 위한 주석 추가 (해당부분 공부필요 )