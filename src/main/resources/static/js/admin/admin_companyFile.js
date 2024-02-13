$(document).ready(function () {
    // 회사문서 업로드
    $(".file_upload_btn").on("click", function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)

        var text = $('#uploadName').val();
        // 선택된 파일 추가
        var formData = new FormData();
        formData.append('file', $('#fileInput')[0].files[0]);
        formData.append('text', text); // text 데이터 추가

        // AJAX 요청 설정
        $.ajax({
            type: 'POST',
            url: 'admin/companyFile/upload',
            data: formData,
            contentType: false,
            processData: false,
            success: function(data) {
                // 성공 시의 동작
                console.log('문서 업로드 성공', data);
                alert(data);
                location.reload(true);
            },
            error: function(error) {
                // 실패 시의 동작
                console.error('문서 업로드 실패', error);
                alert("에러:" + error);
            }
        });
    });
});



$(document).ready(function () {
            // 회사문서 삭제
            $(".file_delete_btn").on("click", function (e) {
                e.preventDefault(); // 기본 동작 방지 (페이지 이동)
                var fileForm = $(this).closest('.uniqueFile');

                // 해당 부모 요소 내에서 file의 id 값을 가져옵니다.
                 var fileId = fileForm.find('.file_delete_btn').val();


                // 서버에 AJAX로 삭제요청 보내기
                $.ajax({
                    type: "GET",
                    url: "/admin/companyFile/delete/"+fileId,
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