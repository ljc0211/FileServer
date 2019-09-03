angular.module("app", []).controller("controller", function($scope) {
    $scope.login = function() {
        window.location.href = "./main.jsp";
    };
});

// angular.module("app", []).controller("controller", function($scope) {
//     $scope.login = function() {
//         $.ajax({
//             url: "user_login.action",
//             data: {
//                 username: $scope.username,
//                 password: $scope.password,
//             },
//             type: "POST",
//             window.location.href = "../main.jsp";
//
//             // beforeSend: function() {},
//             // complete: function() {},
//             // success: function(data) {
//             //     if (data.result === "success") {
//             //         window.location.href = data.url;
//             //     } else {
//             //         alert(data.result);
//             //     }
//             // },
//             // error: function() {
//             //     alert("请求错误");
//             // }
//
//         });
//     };
// });