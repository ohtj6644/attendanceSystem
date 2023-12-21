$(document).ready(function () {
    // "조회하기" 버튼 클릭 이벤트 처리
    $("#searchBtn").on("click", function () {
        // 폼 데이터 가져오기
        var formData = $("#attendanceForm").serialize();

        // 서버에 AJAX 요청 보내기
        $.ajax({
            type: "POST",
            url: "/user/monthAttendance/",
            data: formData,
            success: function (data) {
                // 새로운 데이터로 테이블 본문 업데이트
                updateTable(data.content);
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

        // 새로운 데이터를 순회하며 테이블에 행 추가
        $.each(attendanceList, function (index, attendance) {
            var row = "<tr>" +
                "<td>" + attendance.startWorkTime + "</td>" +
                "<td>" + attendance.endWorkTime + "</td>" +
                "<td>" + attendance.workTime + "</td>" +
                "<td>" + attendance.annual + "</td>" +
                "</tr>";

            tableBody.append(row);
        });
    }
});