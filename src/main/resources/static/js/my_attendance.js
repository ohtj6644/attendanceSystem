$(document).ready(function () {
    // "조회하기" 버튼 클릭 이벤트 처리
    $("#searchBtn").on("click", function () {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        //선택했던 조회기간 데이터 가져오기.

        // 서버에 AJAX POST 요청 보내기
        $.ajax({
            type: "POST",
            url: "/user/monthAttendance/",
            data: JSON.stringify({
                startDate: startDate,
                endDate: endDate
                // Json 형태로 서버에 보냄.
            }),
            contentType: "application/json; charset=utf-8",
        })
            .done(function (data) {
                // 새로운 데이터로 테이블 본문 업데이트
                updateTable(data);
                //받아욘 Page 데이터를 updateTable 에 넘겨서 처리
                alert("검색완료");
            })
            .fail(function (error) {
                console.error("오류:", error);
            });
    });

    function updateTable(page) {
        var tableBody = $("tbody");

        // 기존 행 삭제
        tableBody.empty();

        if (page.content.length > 0) {
            // 받아온 page 데이터가 있을 때 테이블에 행 추가
            $.each(page.content, function (index, attendance) {
                var jsDate1 = new Date(attendance.startWorkTime);
                var jsDate2 = new Date(attendance.endWorkTime);

                var startdDate = jsDate1.getFullYear() + " 년 " +
                    (jsDate1.getMonth() + 1) + "월 " +
                    jsDate1.getDate() + "일 " +
                    jsDate1.getHours() + "시 " +
                    jsDate1.getMinutes() + "분";

                var endDate = jsDate2.getFullYear() + " 년 " +
                    (jsDate2.getMonth() + 1) + "월 " +
                    jsDate2.getDate() + "일 " +
                    jsDate2.getHours() + "시 " +
                    jsDate2.getMinutes() + "분";
                // 받아온 근무의 날짜 및 시간을 원하는 형식으로 포맷팅


                var row = "<tr>" +
                    "<td>" + startdDate + "</td>" +
                    "<td>" + endDate + "</td>" +
                    "<td>" + parseInt(attendance.workTime/60) + "시간" + attendance.workTime%60+"분" + "</td>" +
                    "<td>" + attendance.annual + "</td>" +
                    "</tr>";

                tableBody.append(row);
            });
        } else {
            // 데이터가 없을 때 메시지 표시
            var messageRow = "<tr><td colspan='4'>해당 조회기간의 근무가 없습니다.</td></tr>";
            tableBody.append(messageRow);
        }
    }
});