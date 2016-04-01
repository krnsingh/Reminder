angular.module('web-reminder', [])
    .directive('popupDirective', function() {
        return {
            restrict: 'EAC',
            replace: true,
            template: '<a href="http://google.com">Click me to go to Google</a>'
        };
    });
