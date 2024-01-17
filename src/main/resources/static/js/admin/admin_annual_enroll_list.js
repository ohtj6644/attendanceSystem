$(document).ready(function () {
    // 휴가승인
    $(".enroll_ok_btn").on("click", function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)
        var usersForm = $(this).closest('.uniqueEnroll');

        // 해당 부모 요소 내에서 enroll_ok_btn 의 값을 가져옵니다.
        var annualId = usersForm.find(".enroll_ok_btn").val();

        // 서버에 AJAX 요청 보내기
        $.ajax({
            type: "GET",
            url: "/admin/annual/enroll/ok/"+annualId,
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





$(document).ready(function () {
    // 휴가승인
    $(".enroll_no_btn").on("click", function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)
        var enrollForm = $(this).closest('.uniqueEnroll');

        // 해당 부모 요소 내에서 enroll_ok_btn 의 값을 가져옵니다.
        var annualId = enrollForm.find(".enroll_no_btn").val();

        // 서버에 AJAX 요청 보내기
        $.ajax({
            type: "GET",
            url: "/admin/annual/enroll/no/"+annualId,
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

