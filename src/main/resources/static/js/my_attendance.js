$(document).ready(function () {
    // "조회하기" 버튼 클릭 이벤트 처리
    $("#searchBtn").on("click", function () {
        // 폼 데이터 가져오기
        var formData = new FormData(document.getElementById("attendanceForm"));


        // 서버에 AJAX 요청 보내기
        $.ajax({
            type: "POST",
            url: "/user/monthAttendance/",
            data: formData,
            contentType: false,
            processData: false,
            success: function (data) {
                // 새로운 데이터로 테이블 본문 업데이트
                updateTable(data);
            },
            error: function (error) {
                console.error("오류:", error);
            }
        });
    });

    // 테이블을 새로운 데이터로 업데이트하는 함수
    function updateTable(attendanceList) {
        var tableBody = $("tbody");

        // 기존 행 삭제
        tableBody.empty();

        if (attendanceList.length > 0) {
            // 데이터가 있을 때 테이블에 행 추가
            $.each(attendanceList, function (index, attendance) {
                var row = "<tr>" +
                    "<td>" + attendance.startWorkTime + "</td>" +
                    "<td>" + attendance.endWorkTime + "</td>" +
                    "<td>" + attendance.workTime + "</td>" +
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