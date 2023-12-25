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

- [로그인페이지 html/css 추가]_004_front
  1. 로그인페이지 디자인 추가 (codepen 외부작업 )


### 2023-12-04 
- [메인페이지 추가]_004_front
  1. 메인페이지 템플릿 추가
  2. 틀 작업중 
  
- [병합 / 근무시작 js 연동]_004_front


- [근무기능 활성화 작업브랜치 생성]_005_attendance



- [퇴근기능 활성화 / 업무시간 타이머 추가 ]_005_attendance



### 2023-12-05


- [ 공지사항 엔티티 생성 ]_005_attendance


### 2023-12-07


- [ 공지사항 생성로직 작업중  ]_005_attendance



- [git 규칙 변경] 
  

    1. 메인브랜치에 커밋푸시 진행 
    2. [커밋메시지]_작성자   
  


- [새 작업브랜치 생성]_006_otj


- [메인페이지 공지사항 프론트 html/css 추가작업 ]_otj

### 2023-12-09
- [메인페이지 공지사항 js 작업중 ]_otj

### 2023-12-10
- [공지사항 등록 완료]_otj
  1. 공지사항 관리자 등록 완료.
  2. 로그아웃 기능 추가 필요. (새 아이디 접속 후 관리자권한 작동하는지 테스트 필요)
  3. 공지사항 수정 / 삭제 기능 추가 필요 

- [주석 수정 ]_otj


### 2023-12-11

- [로그아웃 기능 추가 ]_otj
- [깃 꼬임 수정중]_otj 


### 2023-12-13
- [로그아웃 테스트 및 오류수정 / 수정버튼 생성]_otj
  1. 버튼만 생성. 수정권한 / 수정기능 프론트/백엔드 추가해야함 . 

### 2023-12-14
- [공지사항 수정 프론트작업 ]_otj
  1. 공지사항 삭제, 수정버튼 안됨 수정필요 / 권한으로 가시설정 



### 2023-12-15
- [공지사항 삭제기능 완료]_otj
  1. 수정기능 필요 
  2. 수정기능 끝나고 스크립트 html 에서 독립 필요 
  

- [공지사항수정 Backend 작업완료]_otj
  1. 추후 산출물 넣을 output 폴더 추가 
  2. 공지사항 수정 Backend 작업완료
 
- [공지사항 수정 Front Ajax로직 추가]_otj
  1. 수정버튼 클릭 시 해당 수정폼 보이는 작업 추가필요
  2. 공지사항 수정 로직 ajx 추가 

  
### 2023-12-17
- [공지사항수정 작업중]_otj
   1. 공지사항 수정 ajax 오류 해결
   2. 공지사항수정 test 완료
   3. 프론트 수정폼 가시설정 수정필요
 
### 2023-12-18
- [공지사항수정 Front 완료]_otj
  1. 공지사항 수정폼 가시/비가시 수정완료
( js .closest 함수를 이용하여 부모클래스를 찾아 해당 클래스에 포함된 폼만 display 속성이 변경되도록 수정 . )

  2.  공지사항 수정요청 수정완료 
( js .closest 함수를 이용하여 부모클래스를 찾아 해당 클래스에 포함된 내용 과 공지사항 id 를 가져와 요청을 보내도록 수정)



- [메인페이지 js 분리]_otj
  1. 메인페이지 html/js 분리. 
  2. 로그아웃 메세지 수정. 
  3. (예정) 근무리스트  front 작업시작.
  
- [나의 근무내역 작업시작]_otj
  1. My_Attendance.html 페이지 기반작업. 
  2. 프론트 화면 디자인중 
  
- [근무종료 Mapping 변경]_otj
  1. Get>Put 맵핑변경. 
  2. 근무시간타이머 에러 수정필요. ★★★★★★★★
    2-1. 타이머 시간이 이상하게나옴 / 타이머 근무시작 후 늦게나옴.

### 2023-12-20

- [공지사항 삭제 오류수정 및 맵핑변경]_otj
  1. 공지사항 삭제 오류 수정.
  2. 공지사항삭제 맵핑 get>delete 로 변경 


### 2023-12-21
- [근무내역 리스트 프론트 화면 작업중 ]_otj
  1. 근무내역 페이지 기간조회 프론트작업 추가.
  2. 근무내역 백엔드 로직 수정필요
  3. 근무내역 리스트 추가 필요 
  
- [근무내역리스트 백엔드 로직 추가 ]_otj
  1. 백엔드 api 로직 추가 . 
  2. js 작업후 연동필요
  3. 리스트 받을 프론트작업 필요
     
- [근무내역리스트 프론트화면 / js 추가]_otj
  1. 테스트 후 추가수정 필요시 수정작업 필요 

- [근무리스트 리스트화면작업/디버깅중 ]_otj
  1. 근무 리스트 프론트화면 추가완료
  2. 근무내역 로드중 무한루프로 인해 엔티티 참조 해결
  3. 정상적으로 데이터 가져오지 못하는 오류 디버깅 필요 
  

- [근무리스트 리스트화면 프론트엔드/백엔드 연동 ]_otj
  1. AttendanceController > getSearchAttendance메서드에서 
     LocalDateTime 으로 변환하여 찾는부분에서 오류발생하여 
     js 수정 , Js에서 받은 데이터를 'LocalDate' 로 변환 후
     LocalDate 기준 기간으로 Repository에서 가져오는 걸로 로직 수정.
     
  2. 화면 기간조회 선택창 time 삭제하여 날짜만 선택하게 변경 필요.
     
  3. 근무시간타이머 에러 수정필요. ★★★★★★★★
     타이머 시간이 이상하게나옴 / 타이머 근무시작 후 늦게나옴.
     
- [근무리스트 로직 주석추가]_otj
  1. Controller / js 코드 주석 추가. 
  2. 근무리스트 프론트에서 page 처리 필요함 
  
### 2023-12-25 
- [근무리스트 총 근무시간 추가 ]_otj
  1. 근무리스트 조회근무 총 수 / 총 시간 표기 추가
  2. 근무리스트조회 기간 3달로 제한 

- [관리자페이지 작업 시작 ]_otj


- [관리자페이지 레이아웃 추가]_otj
  1. 관리자페이지 네비바/헤더 화면디자인 추가 
  2. 관리자페이지 메인 = 구성원추가 (회원가입)
  3. 회원가입시 실명/입사일 추가하도록 로직 수정 

- [관리자페이지 구성원추가로직 (회원가입)]_otj
  1. 메인페이지에서 회원가입로직 추가. 
  2. 회원가입로직 ajax 통신으로 리팩토링 필요 
  3. 메인페이지 관리자화면 버튼 추가 
  
### 2023-12-26
- [유저페이지 관리자버튼 권한부여]_otj
  1.  관리자가 아닌 유저도 관리자화면 버튼이 나타나는 것 수정. 

- [유저생성 js 추가 / 유저목록 폼 작성중 ]_otj