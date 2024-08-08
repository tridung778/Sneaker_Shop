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
		$scope.form = {
			created_at: new Date(),
			image: '/images/cloud-upload.jpg',
			available: true
		};
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
			Swal.fire({
				icon: "success",
				title: "Thêm Thành công",
				text: "Sản phẩm mới đã được thêm vào danh sách",
			});
		}).catch(error => {
			Swal.fire({
				icon: "error",
				title: "Lỗi",
				text: "Thêm mới sản phẩm gặp trục trặc",
			});
			console.log("cc");
		});
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/admin/products/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[item] = item
			$scope.reset();
			Swal.fire({
				icon: "success",
				title: "Cập nhật sản phẩm Thành công",
				text: "Sản phẩm đã được cập nhật",
			});
		}).catch(error => {
			Swal.fire({
				icon: "error",
				title: "Lỗi",
				text: "Cập nhật sản phẩm gặp trục trặc",
			});
		})
	};


	$scope.delete = function(item) {
		$http.delete(`/admin/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			Swal.fire({
				icon: "success",
				title: "Xóa sản phẩm Thành công",
				text: "Sản phẩm đã được xóa khỏi danh sách",
			});
		}).catch(error => {
			Swal.fire({
				icon: "error",
				title: "Lỗi",
				text: "Xóa sản phẩm gặp trục trặc",
			});
		});
	}

	$scope.imageChaged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi hình ảnh!");
			console.log(error);
		})
	}
	
	$scope.pager = {
		page: 0,
		size: 4,
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
			this.page = this.count -1;
		}
	}


	$scope.load_all();
	$scope.reset();
});
