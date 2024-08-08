const app = angular.module("app", []);
app.controller("ctrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.load_all = function() {
		var url = `/rest/accounts`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
		}).catch(error => {
			console.log("Error", error);
		});
	}
	$scope.reset = function() {
		$scope.form = {};
		$scope.form.photo = '/images/cloud-upload.jpg';
		$scope.key = null;
	}

	$scope.edit = function(id) {
		var url = `/rest/accounts/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch(error => {
			console.log("Error", error);
		});
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		item.role = $scope.form.role;
		var url = `/rest/accounts`;
		$http.post(url, item).then(resp => {
			$scope.items.push(item);
			console.log("Success", resp);
			Swal.fire({
				icon: "success",
				title: "Thêm Thành công",
				text: "Tài khoản mới đã được thêm vào danh sách",
			});
			$scope.reset();
		}).catch(error => {
			console.log("Error", error);
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		var url = `/rest/accounts/${$scope.form.id}`;
		$http.put(url, item).then(resp => {
			var index = $scope.items.findIndex(item => item.id == $scope.form.id);
			$scope.items[index] = resp.data;
			console.log("Success", resp);
			Swal.fire({
				icon: "success",
				title: "Cập nhật sản phẩm Thành công",
				text: "Tài khoản đã được cập nhật",
			});
			$scope.reset();
		}).catch(error => {
			console.log("Error", error);
		});
	}

	$scope.delete = function(id) {
		var url = `/rest/accounts/${id}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.id == id);
			$scope.items.splice(index, 1);
			$scope.reset();
			console.log("Success", resp);
			Swal.fire({
				icon: "success",
				title: "Xóa sản phẩm Thành công",
				text: "Tài khoản đã được xóa",
			});
		}).catch(error => {
			console.log("Error", error);
		});
	}

	$scope.imageChaged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi hình ảnh!");
			console.log(error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 3,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

	$scope.load_all();
	$scope.reset();
});
