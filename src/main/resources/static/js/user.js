let host = "http://localhost:8080/admin";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];
    $scope.load_all = function () {
        var url = `${host}/user`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }
    $scope.reset = function () {
        $scope.form = {};
        $scope.key = null;
    }

    $scope.edit = function (id) {
        var url = `${host}/user/${id}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/user`;
        $http.post(url, item).then(resp => {
            $scope.items.push(item);
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/user/${$scope.form.id}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.id == $scope.form.id);
            $scope.items[index] = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.delete = function (id) {
        var url = `${host}/user/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(item => item.id == id);
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.load_all();
    $scope.reset();
});
