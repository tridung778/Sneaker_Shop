
var app = angular.module("app", []);
app.controller("ctrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.cates = [];
	$scope.load_all = function() {
		$http.get(`/admin/products`).then(resp => {
			$scope.items = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		})
		$http.get("/rest/categories").then(resp => {
			$scope.cates = resp.data;
		})
	}
	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
	}

	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	};

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/admin/products`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Create Success");
		}).catch(error => {
			alert("Error");
			console.log("cc");
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/admin/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[item] = item
			$scope.reset();
			alert("Update Success");
		}).catch(error => {
			alert("Error");
		})
	};


	$scope.delete = function(item) {
		$http.delete(`/admin/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
			console.log(error);
		});
	}


	$scope.load_all();
	$scope.reset();
});
