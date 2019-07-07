'use strict';
angular
    .module('app')
        .controller('employeecreateCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.init = function(){
        		$scope.getAllEmployeeRole();
        	}
        	
        	$scope.getAllEmployeeRole = function(){
        		$http.post('/employee/getAllEmployeeRole')
		    	.then(function(response){
		    		$scope.roles = response.data;
		    	}).catch(function(error){
		    		console.log(error);
		    		
		    	})
        	}
        	
 
        	$scope.insertEmployee = function(){
        		$http.post('/employee/insertEmployee?createdBy='+currentAuth.uid, $scope.employee)
		    	.then(function(response){
		    		$scope.beforeSearch = true;
		    		new PNotify({
                        title: 'Create Success',
                        text: 'This employee is already inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error',
                        text: 'Something wrong',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
	
	
        	
        	
	
}]);



