angular.module('web-reminder', ['ngDialog'])
    .controller('reminderController', function ($scope, $http, ngDialog) {

        $http.get("http://localhost:8080/Reminder/rest/reminder/calendar/0")
            .then(function (response) {
                $scope.calendar = response.data;
                console.log(response.data);
            });


        $scope.displayPopup = function(date) {
            console.log(date);
            ngDialog.open({
                template: 'popup.html',
                className: 'ngdialog-theme-default',
                controller: 'popupController'
            });
        };

    })
    .controller('popupController', function ($scope, $http) {
        $scope.popup = "opoptext";

    })