const app = angular.module('myApp', [])

app.controller('myController', function($scope, $http) {

	const cartUrl = "http://localhost:8080/cart"
	const accountUrl = "http://localhost:8080/rest/accounts"

	$scope.listItem = [];
	$scope.accountID = "";
	$scope.totalPrice = 0;
	$scope.totalQuantity = 0;
	$scope.account = {};

	$scope.loadCart = function(accountID) {
		$http.get(`${cartUrl}/${accountID}`).then(resp => {
			$scope.listItem = resp.data;
			$scope.accountID = accountID;
			$scope.caculateTotal()
			// console.log($scope.accountID)
			// console.log($scope.listItem)
		})
	}

	$scope.addItem = function(productID) {
		const accountID = $scope.accountID;
		$http.post(`${cartUrl}/add?productID=${productID}&accountID=${accountID}`).then(resp => {
			$scope.loadCart(accountID);
			console.log('add success');
			Swal.fire({
				icon: "success",
				title: "Thành công",
				text: "Sản phẩm đã được thêm vào giỏ hàng",
			});
		})
	}

	$scope.deleteItem = function(itemID) {
		const accountID = $scope.accountID;
		$http.delete(`${cartUrl}/delete/${itemID}`).then(resp => {
			$scope.loadCart(accountID);
			console.log('delete success')
			Swal.fire({
				icon: "success",
				title: "Thành công",
				text: "Đã xóa sản phẩm khỏi giỏ hàng",
			});
		})
	}

	$scope.updateItem = function(itemID) {
		const accountID = $scope.accountID;
		$http.put(`${cartUrl}/update/${itemID}`).then(resp => {
			$scope.loadCart(accountID);
			console.log('update success')
		})
	}

	$scope.caculateTotal = function() {
		$scope.totalPrice = 0;
		$scope.totalQuantity = 0;
		for (let i = 0; i < $scope.listItem.length; i++) {
			$scope.totalPrice += $scope.listItem[i].price * $scope.listItem[i].quantity;
			$scope.totalQuantity += $scope.listItem[i].quantity;
		}
	}

	$scope.payment = async function(paymentMethod) {
		console.log(paymentMethod)

		if ($scope.listItem.length === 0) {
			Swal.fire({
				icon: "error",
				title: "Thanh toán thất bại",
				text: "Giỏ hàng của bạn đang trống",
			});
			return;
		}

		console.log("Phương thức thanh toán:", paymentMethod);

		if (paymentMethod === "paypal") {
			const curUrl = "https://api.currencyapi.com/v3/latest?apikey=cur_live_cr1xQRu24tUzUoh1vjJEcg8keRZrjUU2JiLfCnKo";
			let currencyValue = 0;
			try {
				const response = await $http.get(curUrl);
				console.log("Response từ API currency:", response.data);
				if (response.data && response.data.data && response.data.data.VND) {
					currencyValue = response.data.data.VND.value;
					console.log("Giá trị VND:", currencyValue);
				} else {
					console.log("Không tìm thấy dữ liệu VND");
					return;
				}
			} catch (error) {
				console.error("Lỗi khi lấy dữ liệu currency:", error);
				return;
			}

			try {
				const totalPriceInUSD = $scope.totalPrice / currencyValue;
				console.log("Tổng giá trị tính theo USD:", totalPriceInUSD);
				const paymentResponse = await $http.put(`${cartUrl}/payment-paypal?totalPrice=${totalPriceInUSD}&userName=${$scope.accountID}`);
				console.log("Response từ API PayPal:", paymentResponse.data);
				if (paymentResponse.data && paymentResponse.data.redirectUrl) {
					window.location.href = paymentResponse.data.redirectUrl;
				} else {
					console.log("Không có URL chuyển hướng từ PayPal");
				}
			} catch (error) {
				console.error("Lỗi khi gọi API PayPal:", error);
			}
		} else if (paymentMethod === "cod") {
			try {
				const response = await $http.put(`${cartUrl}/payment-cod`);
				console.log("Response từ API COD:", response.data);
				$scope.loadCart($scope.accountID);
				// Thêm logic xử lý cho thanh toán khi nhận hàng
				Swal.fire({
					icon: "success",
					title: "Thành công",
					text: "Đặt hàng thành công",
				});
			} catch (error) {
				console.error("Lỗi khi gọi API COD:", error);
			}
		} else {
			Swal.fire({
				icon: "error",
				title: "Thanh toán thất bại",
				text: "Vui lòng chọn phương thức thanh toán",
			});
		}
		
		
	};

	$scope.loadAccount = function(accountID) {
		$http.get(`${accountUrl}/${accountID}`).then(resp => {
			$scope.account = resp.data;
		})
	}

	$scope.updateAccount = function() {
		$http.put(`${accountUrl}/${$scope.accountID}`, $scope.account).then(resp => {
			console.log(resp)
			Swal.fire({
				icon: "success",
				title: "Thành công",
				text: "Cập nhật hồ sơ thành công",
			});
		})
	}

	$scope.imageChaged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.account.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi hình ảnh!");
			console.log(error);
		})
	}
}
)
