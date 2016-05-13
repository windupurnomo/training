var app = angular.module('app', ['ui.router', 'angular-loading-bar']);

app.constant('config', {
    url: 'http://localhost:8080/api'
});

app.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
    $stateProvider.state('home', {
        url: '/',
        templateUrl: 'tpl/home.html',
        controller: "HomeCtrl"
    })

    .state('form', {
        url: '/form/:id',
        templateUrl: 'tpl/form.html',
        controller: "FormCtrl"
    });

    $urlRouterProvider.otherwise('/');
});
