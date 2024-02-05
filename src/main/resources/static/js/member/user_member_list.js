$(document).ready(function () {
    // 검색 버튼 클릭 시
    $('#searchBtn').click(function (e) {
        e.preventDefault(); // 기본 동작 방지 (페이지 이동)
        var keyword = $("#keyword").val();

        // Ajax 요청
        $.ajax({
            type: 'GET',
            url: "/member/search/"+keyword,
            success: function (data) {
                // .list-item의 내용을 업데이트
                updateListItems(data);
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

    // .list-item의 내용을 업데이트하는 함수
    function updateListItems(data) {
        // 리스트 아이템을 초기화하고 새로운 데이터로 채우기
        var $list = $(".list");
        $list.empty();

        data.forEach(function (user) {
            var listItem = '<li class="list-item">';
            listItem += '<img src="' + user.avatarUrl + '" height="100" width="100" alt="' + user.login + '" class="img-circle center-block" />';
            listItem += '<h1>' + user.realName + '</h1>';
            listItem += '</li>';
            $list.append(listItem);
        });
    }
});