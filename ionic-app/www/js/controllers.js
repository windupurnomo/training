angular.module('starter.controllers', [])

.controller('AppCtrl', function($scope, $ionicModal, $timeout) {})

.controller('UsersCtrl', function($scope, $http) {
    var url = "http://localhost:8080/api/user";
    $scope.users = [];
    $http.get(url).then(function(res) {
        $scope.users = res.data;
    })
})

.controller('UserCtrl', function($scope, $state, $stateParams, $http) {
    var url = "http://localhost:8080/api/user";
    var id = $stateParams.userId;
    var isCreate = id == -1;
    if (!isCreate) {
        $http.get(url + "/" + id).then(function(res) {
            $scope.user = res.data;
        });
    }

    $scope.save = function (){
    	var data = angular.copy($scope.user);
    	if (isCreate){
    		$http.post(url, data).then(success);
    	}else{
    		$http.put(url, data).then(success);
    	}
    }

    var success = function (){
    	$state.go("app.users");
    }
});
