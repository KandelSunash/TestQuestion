angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {
            $http({method: 'GET', url: 'api/load-data'}).then(function (response) {
                $scope.states = ["IL ", "MN ", "NJ ", "NY ", "OK "];
                $scope.selectedState = $scope.states[0];
                $scope.result =  response.data;
                console.log( Object.values(response.data));
            }, function (reason) {
                console.log('error ' + reason)
            });

        }]);
