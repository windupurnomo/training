app.controller("HomeCtrl", function($scope, $http, $state, $stateParams, config) {
    var url = config.url + "/user";
    $scope.customers = [];
    $http.get(config.url + '/user').then(function(res) {
        $scope.customers = res.data;
    });

    $scope.detail = function(id){
    	console.log(id)
    	$state.go("form", {id: id});
    }

})