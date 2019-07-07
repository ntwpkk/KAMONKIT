'use strict';
angular
    .module('app')
        .controller('colorCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	$scope.showInstallmentTable = false;
        	
        	$scope.init = function(){
        		$scope.getAllColor();
        	}
        	
        	
        	$scope.getAllColor = function(){
        		$http.post('/parameter/getAllColorData')
		    	.then(function(response){
		    		$scope.colors = response.data;
		    		console.log($scope.colors);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.clickEditColor = function(color){
        		$scope.insertColor=false;
        		$scope.colorUpdate = color;
        	}
        	
        	$scope.saveColor = function(){
        		$http.post('/parameter/insertColor?createdBy='+currentAuth.uid , $scope.colorInsert)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Create Success',
                        text: 'This color is already inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.getAllColor();
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: error.data.error,
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
        	
        	$scope.editColor = function(){
        		$http.post('/parameter/updateColor?updatedBy='+currentAuth.uid , $scope.colorUpdate)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Update Success',
                        text: 'This color is already updated to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.getAllColor();
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: error.data.error,
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
        	
}]);



