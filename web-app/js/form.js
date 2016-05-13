app.controller("FormCtrl", function($scope, $http, $state, $stateParams, config) {
    var url = config.url + "/user";
    $scope.user = {};
    if ($stateParams.id){
    	$scope.isUpdate = true;
    }

    var init = function() {
        if ($stateParams.id) {
            $http.get(url + "/" + $stateParams.id).then(function(res) {
                $scope.user = res.data;
            });
        }
    }

    init();

    $scope.save = function() {
        var data = angular.copy($scope.user);
        if ($scope.isUpdate) {
            $http.put(url, data).then(success);
        } else {
            $http.post(url, data).then(success)
        }

    }

    $scope.delete = function() {
        if ($scope.user == null) {
            alert("Silahkan pilih user terlebih dulu...");
        } else {
            if (confirm("Apakah anda yakin akan menghapus " + $scope.user.name + "?")) {
                $http.delete(url + "/" + $scope.user.id).then(success);
            }
        }
    }

    var success = function (){
    	$state.go("home");
    };

});
