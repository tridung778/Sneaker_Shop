const app = angular.module('myApp', [])

app.controller('myController', function ($scope, $http) {

        const url = "http://localhost:8080/cart"

        $scope.listItem = [];
        $scope.accountID = "";
        $scope.totalPrice = 0;
        $scope.totalQuantity = 0;

        $scope.loadCart = function (accountID) {
            $http.get(`${url}/${accountID}`).then(resp => {
                $scope.listItem = resp.data;
                $scope.accountID = accountID;
                $scope.caculateTotal()
                // console.log($scope.accountID)
                // console.log($scope.listItem)
            })
        }

        $scope.addItem = function (productID) {
            const accountID = $scope.accountID;
            $http.post(`${url}/add?productID=${productID}&accountID=${accountID}`).then(resp => {
                $scope.loadCart(accountID);
                console.log('add success')
            })
        }

        $scope.deleteItem = function (itemID) {
            const accountID = $scope.accountID;
            $http.delete(`${url}/delete/${itemID}`).then(resp => {
                $scope.loadCart(accountID);
                console.log('delete success')
            })
        }

        $scope.updateItem = function (itemID) {
            const accountID = $scope.accountID;
            $http.put(`${url}/update/${itemID}`).then(resp => {
                $scope.loadCart(accountID);
                console.log('update success')
            })
        }

        $scope.caculateTotal = function () {
            $scope.totalPrice = 0;
            $scope.totalQuantity = 0;
            for (let i = 0; i < $scope.listItem.length; i++) {
                $scope.totalPrice += $scope.listItem[i].price * $scope.listItem[i].quantity;
                $scope.totalQuantity += $scope.listItem[i].quantity;
            }
        }

        $scope.payment = function () {
            if($scope.listItem.length === 0) {
                alert("Giỏ hàng trống")
                return
            }
            $http.put(`${url}/payment`)
                .then(resp => {
                    $scope.loadCart($scope.accountID);
                })
                .catch(error => {
                    $scope.loadCart($scope.accountID);
                });
            $scope.loadCart($scope.accountID);
        }
    }
)
