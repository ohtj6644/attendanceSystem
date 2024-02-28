
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
