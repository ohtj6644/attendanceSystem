 // "조회하기" 버튼 클릭 이벤트 처리
    $("#bgBtn").on("click", function () {
        var bgDate = $("#bgDate").val();
        var reason = $("#area").val();
        //선택했던 연차 사용일 데이터 가져오기.

        // 서버에 AJAX POST 요청 보내기
        $.ajax({
            type: "POST",
            url: "/bgAttendance/enroll",
            data: JSON.stringify({
                bgDate: bgDate,
                area: area
            }),
            contentType: "application/json; charset=utf-8",
        })
            .done(function (data) {
                alert(data);
            })
            .fail(function (xhr, textStatus, errorThrown) {
                        // HTTP 상태 코드가 400인 경우 오류 메시지 출력
                        if (xhr.status === 400) {
                            alert(xhr.responseText);
                        } else {
                            console.error("오류:", errorThrown);
                        }
                    });
    });