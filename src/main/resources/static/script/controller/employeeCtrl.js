'use strict';
angular
    .module('app')
        .controller('employee2Ctrl', [ '$scope', '$http','currentAuth' , 'Auth' , '$rootScope',function($scope,$http,currentAuth,Auth,rootScope) {
        	
        	
        	$scope.init = function(){
        		$scope.getAllEmployee();
        	}
        	
        	$scope.getAllEmployee = function(){
        		$http.post('/employee/getAllEmployee')
		    	.then(function(response){
		    		$scope.employees = response.data;
		    		console.log($scope.employees);
		    	}).catch(function(error){
		    		console.log(error);
		    		
		    	})
        	}
        	
        	
        	
	
	
	
}]);



