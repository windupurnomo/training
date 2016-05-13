angular.module('app', [])
    .controller("Ctrl", function($scope, $http) {
        var url = "http://localhost:8080/api/user";

        var init = function() {
            $http.get(url).then(function(res) {
                $scope.users = res.data;
            });
        }

        init();

        $scope.change = function(u) {
            $scope.user = angular.copy(u);
        }

        $scope.save = function() {
            var data = angular.copy($scope.user);
            if (data.id === undefined) {
                $http.post(url, data).then(function(res) {
                    $scope.user = null;
                    init();
                })
            } else {
                $http.put(url, data).then(function(res) {
                    $scope.user = null;
                    init();
                });
            }

        }

        $scope.delete = function() {
            if ($scope.user == null) {
                alert("Silahkan pilih user terlebih dulu...");
            } else {
                if (confirm("Apakah anda yakin akan menghapus " + $scope.user.name + "?")) {
                    $http.delete(url + "/" + $scope.user.id).then(function(res) {
                        $scope.user = null;
                        init();
                    })
                }
            }
        }

        $scope.cancel = function (){
        	$scope.user = null;
        }
    });
