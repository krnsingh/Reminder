angular.module('web-reminder', [])
    .controller('reminderController', function ($scope, $http) {

        $http.get("http://localhost:8080/Reminder/rest/reminder/hello")
            .then(function (response) {
                $scope.calendar = response.data;
            });

    });