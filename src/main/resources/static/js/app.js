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

        $scope.payment = async function (paymentMethod) {
            if ($scope.listItem.length === 0) {
                alert("Giỏ hàng trống")
                return
            }

            if (paymentMethod === "paypal") {
                const curUrl = "https://api.currencyapi.com/v3/latest?apikey=cur_live_cr1xQRu24tUzUoh1vjJEcg8keRZrjUU2JiLfCnKo";
                let currencyValue = 0;
                try {
                    const response = await $http.get(curUrl);
                    if (response.data && response.data.data && response.data.data.VND) {
                        currencyValue = response.data.data.VND.value;
                        console.log("VND Value:", currencyValue);
                    } else {
                        console.log("VND data not found");
                        return;
                    }
                } catch (error) {
                    console.error("Error fetching currency data:", error);
                    return;
                }

                try {
                    const paymentResponse = await $http.put(`${url}/payment-paypal?totalPrice=${$scope.totalPrice / currencyValue}&userName=${$scope.accountID}`);
                    if (paymentResponse.data && paymentResponse.data.redirectUrl) {
                        window.location.href = paymentResponse.data.redirectUrl;
                    } else {
                        $scope.loadCart($scope.accountID);
                    }
                } catch (error) {
                    console.error(error);
                    $scope.loadCart($scope.accountID);
                }
            }
            if (paymentMethod === "cod") {
                $http.put(`${url}/payment-cod`)
                    .then(resp => {
                        $scope.loadCart($scope.accountID);
                    })
                    .catch(error => {
                        $scope.loadCart($scope.accountID);
                    });
                $scope.loadCart($scope.accountID);
            }
        }
    }
)
