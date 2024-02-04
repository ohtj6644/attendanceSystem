//new Vue({
//  el: '#app',
//  data: {
//    debug: true,
//    users: [],
//  },
//  mounted: function() {
//    this.loadUsers();
//  },
//  methods: {
//    loadUsers: function() {
//      // Axios를 사용하여 Java 백엔드에서 데이터 가져오기
//      axios.get('/api/users')
//        .then(response => {
//          this.users = response.data.content; // 'content'가 사용자 목록을 포함하는 속성일 것으로 가정합니다.
//        })
//        .catch(error => {
//          console.error('사용자 정보를 가져오는 중 오류 발생:', error);
//        });
//    }
//  }
//});