$(document).ready(function () {
    // 외근 신청취소
    $(".bg_cancel_btn").on("click", function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)
        var uniqueAnnual = $(this).closest('.uniqueBg');

        // 해당 부모 요소 내에서 annual 의 값을 가져옵니다.
        var annualId = uniqueAnnual.find(".bg_cancel_btn").val();

        // 서버에 AJAX 요청 보내기
        $.ajax({
            type: "GET",
            url: "/user/bg/cancel/"+annualId,
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
