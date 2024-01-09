$(document).ready(function () {
        // 회원가입 버튼 클릭 이벤트 처리
        $("#signupBtn").on("click", function () {
            // FormData 객체를 생성하고 폼 데이터 추가
            var formData = new FormData($("form")[0]);

            // 서버에 AJAX POST 요청 보내기
            $.ajax({
                type: "POST",
                url: "/admin/signup",
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    // 성공 시 동작
                    console.log("회원가입 성공:", data);
                    alert(data);
                    location.reload();
                },
                error: function (error) {
                    // 실패 시 동작
                    console.error("회원가입 실패:", error);
                    alert("회원가입 실패");
                }
            });
        });
    });