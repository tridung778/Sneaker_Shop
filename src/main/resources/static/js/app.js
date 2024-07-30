const app = angular.module('myApp', [])

app.controller('myController', function ($scope, $http) {

        const url = "http://localhost:8080/cart"

        $http.get(url)
            .then(function (response) {
                console.log(response)
            })
    }
)