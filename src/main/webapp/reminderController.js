angular.module('web-reminder', [])
    .controller('reminderController', function ($scope, $http) {

        //$http.get("http://localhost:8080/Reminder/rest/reminder/kutta")
        //    .success(function(response) {
        //        $scope.info = response;
        //    });
        $http.get("http://localhost:8080/Reminder/rest/reminder/hello")
            .then(function (response) {
                $scope.info = response.data;
            });

    });