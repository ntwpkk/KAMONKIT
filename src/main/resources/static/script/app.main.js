'use strict';
angular.module('app').controller('AppCtrl', ['$scope', '$http', '$timeout', '$window', '$rootScope', 'Auth',
  function AppCtrl($scope, $http, $timeout, $window, $rootScope, Auth) {
    console.log('app main');
	$scope.title = "";
	$scope.todos = [];
	
	$scope.$watch("init", function(){
	//	$scope.loadTodoList();
	});
	
	$scope.loadTodoList = function(){
		$http.get('/api/todo-list').then(function(response){
			console.log(response);
			$scope.todos = response.data.data;
		}).catch(function(response){
			console.error(response);
		});
	};
	
	$scope.addTodo = function(){
		var postData = {title:$scope.title};
		$http.post('/api/add-todo', postData).then(function(response){
			$scope.loadTodoList();
		}).catch(function(response){
			console.error(response);
		});
	};
	
	$scope.logout = function(){
		console.log("logout");
		  console.log(Auth);
	  }
  }
]);
