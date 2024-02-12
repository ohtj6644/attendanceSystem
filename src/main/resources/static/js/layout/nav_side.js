
//로그아웃

$(document).ready(function () {
    // 로그아웃 버튼 클릭 시
    $('#logout').click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)

        // Ajax 요청
        $.ajax({
            type: 'GET',
            url: '/user/logout',
            success: function (data) {
                // 성공 시 팝업 창에 결과 표시
                alert("로그아웃 되었습니다.");
                location.reload();
            },
            error: function (xhr) {
                // 실패 시 적절한 처리
                if (xhr.status === 400) {
                    alert(xhr.responseText); // 서버에서 전달한 오류 메시지 표시
                } else {
                    console.error('Ajax 요청 실패:', xhr);
                }
            }
        });
    });
});


// 근무시작 스크립트
$(document).ready(function () {
    // 근무 시작 버튼 클릭 시
    $('#startWork').click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)

        // Ajax 요청
        $.ajax({
            type: 'GET',
            url: '/user/startWork',
            success: function (data) {
                // 성공 시 팝업 창에 결과 표시
                alert(data);
                location.reload();
            },
            error: function (xhr) {
                // 실패 시 적절한 처리
                if (xhr.status === 400) {
                    alert(xhr.responseText); // 서버에서 전달한 오류 메시지 표시
                } else {
                    console.error('Ajax 요청 실패:', xhr);
                }
            }
        });
    });
});

//근무종료 스크립트
$(document).ready(function () {
    // 근무 end 버튼 클릭 시
    $('#endWork').click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)

        // Ajax 요청
        $.ajax({
            type: 'PUT',
            url: '/user/endWork',
            success: function (data) {
                // 성공 시 팝업 창에 결과 표시
                alert(data);
                location.reload();
            },
            error: function (xhr) {
                // 실패 시 적절한 처리
                if (xhr.status === 400) {
                    alert(xhr.responseText); // 서버에서 전달한 오류 메시지 표시
                } else {
                    console.error('Ajax 요청 실패:', xhr);
                }
            }
        });
    });
});

// Thymeleaf를 사용하여 Java에서 전달한 startWorkTime 값을 가져옴
var startWorkTime = new Date(/*[[${startWorkTime}]]*/ null);

// 타이머 시작 함수
function startTimer() {
    var timerElement = document.getElementById('timer');

    // 타이머 갱신 함수
    function updateTimer() {
        // 현재 시간을 가져옴
        var currentTime = new Date();

        // 경과 시간 계산 (밀리초)
        var elapsedTimeInMilliseconds = currentTime - startWorkTime;

        // 밀리초를 시간, 분, 초로 변환
        var seconds = Math.floor(elapsedTimeInMilliseconds / 1000);
        var minutes = Math.floor(seconds / 60);
        var hours = Math.floor(minutes / 60);

        // 남은 시간 계산
        var remainingSeconds = seconds % 60;
        var remainingMinutes = minutes % 60;

        // 경과 시간을 화면에 표시
        timerElement.innerHTML = '근무시간 : ' + hours + '시간 ' + remainingMinutes + '분 ' + remainingSeconds + '초';

        // 1초마다 업데이트
        setTimeout(updateTimer, 1000);
    }

    // 타이머 시작
    updateTimer();
}

// 페이지 로드 후 타이머 시작
window.onload = startTimer;



 $(document).ready(function() {
     // 이미지가 클릭되었을 때
     $('.clickable').click(function() {
       // 파일 입력 요소 클릭
       $('#fileInput').click();
     });

     // 파일 입력이 변경되었을 때
     $('#fileInput').change(function() {
       // FormData 객체 생성
       var formData = new FormData();

       // 선택된 파일 추가
       formData.append('file', $('#fileInput')[0].files[0]);

       // AJAX 요청 설정
       $.ajax({
         type: 'POST',
         url: '/profile/upload',
         data: formData,
         contentType: false,
         processData: false,
         success: function(data) {
           // 성공 시의 동작
           console.log('프로필 업로드 성공', data);
            alert(data);
            location.reload(true);
         },
         error: function(error) {
           // 실패 시의 동작
           console.error('파일 업로드 실패', error);
           alert("에러:"+error);
         }
       });
     });
   });
