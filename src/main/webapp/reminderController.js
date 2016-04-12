angular.module('web-reminder', ['ngDialog'])
    .controller('reminderController', function ($scope, $http, ngDialog) {

        $scope.init = function () {
            $scope.getCalender(0);
        };

        $scope.getCalender = function (plusMinusCurrentMonth) {
            if (plusMinusCurrentMonth === 0) {
                var currentDate = new Date();
                $scope.refdate = currentDate;
                var month = currentDate.getMonth() + 1;
                var year = currentDate.getFullYear();
                console.log("0 condition " + $scope.refdate);
            } else {
                currentDate = $scope.refdate;
                currentDate.setMonth(currentDate.getMonth() + plusMinusCurrentMonth);
                $scope.refdate = currentDate;
                month = currentDate.getMonth() + 1;
                year = currentDate.getFullYear();
                console.log("else condition " + $scope.refdate);
            }
            var url = "http://localhost:8080/Reminder/rest/reminder/calendar/" + month + "/" + year;
            console.log(url);
            $http.get(url)
                .then(function (response) {
                    $scope.calendar = response.data;
                    console.log(response.data);
                })
        };


        $scope.displayPopup = function (date) {
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