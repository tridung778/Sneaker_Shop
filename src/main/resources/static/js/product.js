
var app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];
    $scope.load_all = function () {
        $http.get(`/admin/products`).then(resp => {
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
        $http.get(`/admin/products/${id}`).then(resp => {
            $scope.form = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `/admin/products`;
        // Gửi yêu cầu POST đến API
        $http.post(url, item).then(resp => {
            // Kiểm tra nếu API trả về dữ liệu của sản phẩm mới
            if (resp.data) {
                // Thêm sản phẩm mới vào danh sách
                $scope.items.push(resp.data);
                // Reset biểu mẫu
                $scope.reset();
            } else {
                console.log("No data returned from server.");
            }
            // Log thông báo thành công
            console.log("Success", resp);
        }).catch(error => {
            // Log lỗi và có thể thêm thông báo lỗi cho người dùng
            console.log("Error", error);
            alert("An error occurred while creating the product.");
        });
    }


    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `/admin/products/${$scope.form.id}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.id == $scope.form.id);
            $scope.items[index] = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    }

    $scope.delete = function (item) {
        $http.delete(`/admin/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
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
