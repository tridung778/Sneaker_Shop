const app = angular.module('myApp', [])

app.controller('myController', function ($scope, $http) {

        const url = "http://localhost:8080/cart"

        $scope.listItem = [1,1,1];

        // $http.get(url)
        //     .then(function (response) {
        //         console.log(response)
        //     })
    }
)