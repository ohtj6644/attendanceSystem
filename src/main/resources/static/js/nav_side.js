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
                    var popup = window.open('', '_blank', 'width=400,height=200');
                    popup.document.write('<html><head><title>근무 시작 결과</title></head><body>');
                    popup.document.write('<h2>근무 시작 결과</h2>');
                    popup.document.write('<p>' + data + '</p>');
                    popup.document.write('<button onclick="window.close();">닫기</button>');
                    popup.document.write('</body></html>');
                },
                error: function (error) {
                    // 실패 시 적절한 처리
                    console.error('Ajax 요청 실패:', error);
                }
            });
        });
    });

