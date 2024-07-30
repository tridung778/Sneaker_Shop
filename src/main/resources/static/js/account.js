var app = angular.module('app', ['ngRoute']);
app.controller('ctrl', function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.error = '';

	$scope.create = function() {
		if ($scope.form.password !== $scope.form.confirmPassword) {
			$scope.error = 'Mật khẩu không trùng khớp';
			return;
		}

		var item = angular.copy($scope.form);
		item.role = "GUEST";
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Tạo tài khoản thành công");
		}).catch(error => {
			alert("Lỗi khi tạo tài khoản");
		})
	};

	$scope.reset = function() {
		$scope.form = {};
	};
});