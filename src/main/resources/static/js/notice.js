// 채팅 공지사항 등록 스크립트
$(document).ready(function () {
    // 공지사항 버튼 클릭 시
    $("#sendNotice").click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)
        var content = $("#noticeInput").val();

        var formData = new FormData();
        formData.append("noticeText", content);

        $.ajax({
            type: 'POST',
            url: '/notice/create',
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                // 성공 시 팝업 창에 결과 표시
                alert(data);
                location.reload();
                // 성공 후 페이지 리로드
            },
            error: function (xhr) {
                // 실패 시 적절한 처리
                if (xhr.status === 400) {
                    alert(xhr.responseText); // 서버에서 전달한 오류 메시지 팝업창 표시
                } else {
                    console.error('Ajax 요청 실패:', xhr);
                }
            }
        });
    });
});

// 채팅 공지사항 삭제 스크립트
$(document).ready(function () {
    // 공지사항 삭제버튼 클릭 시
    $("#deletenotice").click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)
        var noticeId = $("#deletenotice").val();

        $.ajax({
            type: 'GET',
            url: '/notice/delete/' + noticeId,
            success: function (data) {
                // 성공 시 팝업 창에 결과 표시
                alert(data);
                location.reload();
                // 성공 후 페이지 리로드
            },
            error: function (xhr) {
                // 실패 시 적절한 처리
                if (xhr.status === 400) {
                    alert(xhr.responseText); // 서버에서 전달한 오류 메시지 팝업창 표시
                } else {
                    console.error('Ajax 요청 실패:', xhr);
                }
            }
        });
    });
});
// 채팅 공지사항 수정 스크립트
$(document).ready(function () {
// 공지사항 수정버튼 클릭 시
    $(".btn-modify").click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)

// 수정 버튼이 속한 부모 요소인 .message-candidate를 찾습니다.
        var messageCandidate = $(this).closest('.message-candidate');

// 해당 부모 요소 내에서 noticemodifyInput의 값을 가져옵니다.
        var modifyContent = messageCandidate.find("#noticemodifyInput").val();

// 해당 부모 요소 내에서 sendModifyNotice의 값을 가져옵니다.
        var noticeId = messageCandidate.find(".btn-modify").val();

        $.ajax({
            type: 'PUT',
            url: '/notice/modif/' + noticeId + '?content=' + modifyContent,
            success: function (data) {
// 성공 시 팝업 창에 결과 표시
                alert(data);
                location.reload();
// 성공 후 페이지 리로드
            },
            error: function (xhr) {
// 실패 시 적절한 처리
                if (xhr.status === 400) {
                    alert(xhr.responseText); // 서버에서 전달한 오류 메시지 팝업창 표시
                } else {
                    console.error('Ajax 요청 실패:', xhr);
                }
            }
        });
    });
});
document.addEventListener('DOMContentLoaded', function () {
    var modifyButtons = document.querySelectorAll('.modify-notice-btn');

    modifyButtons.forEach(function (button) {
        button.addEventListener('click', function () {
// 각 수정 버튼이 속한 부모 요소에서 modifyForm을 찾습니다.
            var modifyForm = button.closest('.message-candidate').querySelector('.input-group');

// modifyForm의 display 속성을 'block'으로 변경합니다.
            if (modifyForm) {
                modifyForm.style.display = 'block';
            }
        });
    });
});
