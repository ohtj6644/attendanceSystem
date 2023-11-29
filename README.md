## 근태관리시스템 (사이드 프로젝트)
### SpringBoot / MongoDB /JAVA /RESTful API

### 2023-11-21
- 프로젝트 생성
- 회원가입 구현 / 회원가입 시 uuid 객체 자동생성 및 부여 11 


### 2023-11-21
- 로그인 시작 
- git 규칙 
  
    1. 새 로직 작업 시작 시 000(브랜치번호)_작업명 으로 새 브랜치 생성.
    2. 브랜치명의 작업이 모두 완료될 경우 main에 브랜치 Merge. 
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
     

- [Security 부분 주석 추가]_001_login-2 
  1. 추가 
  


### 2023-11-23 

- [Attendance 생성]_001_login 
  1. attendance 엔티티생성 및 출근 구현 
  2. main 브랜치에 merge

- [Attendance 브랜치 생성]_002_attendance
  1. attendance 브랜치 생성 및 최초 푸쉬 
  

- [Attendance 근무종료]_002_attendance
  1. 근무 종료 메서드 추가 
  2. 근무 시작 추가
  

- [주석 및 설명 추가 ]_002_attendance
  1.  주석 및 설명 추가 ,수정 


### 2023-11-24

- [한달간 근무내역 리스트_1]_002_attendance
  1. 한달간 근무내역 리스트 조회 메서드 작성중 
  

### 2023-11-27

- [한달간 근무내역 리스트_2]_002_attendance
  1. 한달간 근무내역 리스트 조회 기능 추가 완료 
     

- [휴가/연차 작업 브랜치 추가]_003_Annual
  1. 휴가, 연차 관련작업 시작 
  2. 새 브랜치 생성 



- [AnnualCountScheduler 추가]_003_Annual
  1. AnnualCountScheduler 클래스(연차생성 스케줄러) 추가 . 
  2. Application 실행 시 Admin 계정 확인 후 없으면 추가하는 메서드 작성 
  3. controller>rest 라이브러리명 rest>>>api 로 변경 

  % AnnualCountScheduler 수정 필요함 

- [AnnualCountScheduler 수정 , AttendanceScheduler 추가]_003_Annual
  1. AttendanceScheduler= 매일 자정실행/ 전날 퇴근처리를 안한 근무를 삭제하는 스케줄러 
  2. AnnualCountScheduler 연차 / 월차 구분하여 카운트 처리.

### 2023-11-29

- [연월차 카운트 수정 ]_003_Annual 
- 연월차 스케줄러 및 엔티티 수정 작업중 


- [볗합]_003_Annual 
  1. Main 에 병합 후 프론트 작업 시작 예정 
  2. 휴가신청로직 필요함
  3. 관리자로직 필요함
  

- [프론트 페이지 작업시작]_004_front