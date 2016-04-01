angular.module('web-reminder', [])
    .controller('reminderController', function ($scope, $http) {

        $http.get("http://localhost:8080/Reminder/rest/reminder/calendar/0")
            .then(function (response) {
                $scope.calendar = response.data;
                console.log(response.data);
            });

    })
    .directive('popupDirective', function () {
        return {
            restrict: 'EAC',
            replace: true,
            template: '<a href="http://google.com">Click me to go to Google</a>'
        };
    });