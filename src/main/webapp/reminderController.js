angular.module('web-reminder',[])
    .controller('reminderController',$scope, $http, function($scope, $http) {

        $http.get("http://localhost:8080/Reminder/rest/reminder/kutta")
            .success(function(response) {
                $scope.info = response;
            })


    });