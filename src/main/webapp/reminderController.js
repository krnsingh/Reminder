angular.module('web-reminder', ['ngDialog'])
    .controller('reminderController', function ($scope, $http, ngDialog) {

        $scope.isReminderEmailSet = false;

        $scope.submitReminderEmail = function (reminderEmail) {
            $scope.isReminderEmailSet = true;
            $scope.reminderEmail = reminderEmail;
            console.log($scope.reminderEmail);
            $scope.getCalender(0);
        };

        $scope.isCurrentMonth = function () {
            return $scope.refdate.getMonth() === new Date().getMonth();
        };

        $scope.isPreviousDate = function (date) {
            console.log(date);
            return false;
        };

        $scope.refreshCalender = function () {
            var month = $scope.refdate.getMonth() + 1;
            var year = $scope.refdate.getFullYear();
            var url = "http://localhost:8080/Reminder/rest/reminder/calendar/" + $scope.reminderEmail + "/" + month + "/" + year;
            console.log(url);
            $http.get(url)
                .then(function (response) {
                    $scope.calendar = response.data;
                    console.log(response.data);
                })
        }

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
            var url = "http://localhost:8080/Reminder/rest/reminder/calendar/" + $scope.reminderEmail + "/" + month + "/" + year;
            console.log(url);
            $http.get(url)
                .then(function (response) {
                    $scope.calendar = response.data;
                    console.log(response.data);
            })
        };



        $scope.displayPopup = function (date) {
            $scope.selectedDate = date.localDate;
            ngDialog.open({
                template: 'popup.html',
                className: 'ngdialog-theme-default',
                controller: 'popupController',
                scope: $scope
            });
        };

        $scope.reminderCancelPopup = function (day) {
            var month = $scope.refdate.getMonth() + 1;
            var year = $scope.refdate.getFullYear();
            var url = "http://localhost:8080/Reminder/rest/reminder/alerts/" +  $scope.reminderEmail
                +   "/" +   day + "/" + month + "/" + year;
            console.log("URL",url);
            $http.get(url)
                .then(function (response) {
                    $scope.alerts = response.data;
                    console.log("Alerts from, backend - ", response.data);
            })
            ngDialog.open({
                template: 'cancel.html',
                className: 'ngdialog-theme-default',
                controller: 'cancelReminderController',
                scope: $scope
            });
        };
    })
    .controller('popupController', function ($scope, $http, ngDialog) { // popup ctrl
        console.log("Selected Date", $scope.selectedDate);
        $scope.timeHrs = ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"];
        $scope.timeMins = ["00", "15", "30", "45"];

        $scope.submitReminder = function () {
            console.log("selectedHr - ", $scope.selectedHr);
            console.log("selectedMin - ", $scope.selectedMin);
            console.log("selectedEmail - ", $scope.reminderEmail);
            console.log("selectedRem - ", $scope.reminder);
            var alterUrl = "http://localhost:8080/Reminder/rest/reminder/create";
            var alterData = {
                "email": $scope.reminderEmail,
                "msg": $scope.reminder,
                "month": $scope.selectedDate.monthValue,
                "year": $scope.selectedDate.year,
                "hh": $scope.selectedHr,
                "mm": $scope.selectedMin,
                "day": $scope.selectedDate.dayOfMonth
            };


            $http.post(alterUrl, alterData).then(function (response) {
                    console.log("Success", response.data.responseMsg);
                    $scope.closeThisDialog();
                    ngDialog.open({
                        template: '<div><b>' + response.data.responseMsg + '</b></div>',
                        plain: true,
                    });
                    $scope.refreshCalender();
                },
                function (response) {
                    console.log("failed " + response);

                });


        }

    })
    .controller('cancelReminderController', function ($scope, $http, ngDialog) {

        $scope.deleteAlert = function (alertId) {
            console.log("cancel called **** ", alertId);
            $http.delete("http://localhost:8080/Reminder/rest/reminder/alerts/"+alertId)
                .then(
                function(response){
                    for (i = 0; i < $scope.alerts.length; i++) {
                        if($scope.alerts[i].id === alertId) {
                            $scope.alerts.splice(i,1);
                            $scope.refreshCalender();
                        }
                    }
                },
                function(response){
                    console.log(response);
                }
            );
        }

    })
