'use strict';
angular
    .module('app')
        .controller('nameTitleCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	$scope.showInstallmentTable = false;
        	
        	$scope.init = function(){
        		$scope.getAllNameTitle();
        	}
        	
        	
        	$scope.getAllNameTitle = function(){
        		$http.post('/parameter/getAllNameTitleData')
		    	.then(function(response){
		    		$scope.nameTitles = response.data;
		    		console.log($scope.nameTitles);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.clickEditNameTitle = function(NameTitle){
        		$scope.insertNameTitle=false;
        		$scope.nameTitleUpdate = NameTitle;
        	}
        	
        	$scope.saveNameTitle = function(){
        		$http.post('/parameter/insertNameTitle?createdBy='+currentAuth.uid , $scope.nameTitleInsert)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Create Success',
                        text: 'This Name Title is already inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.getAllNameTitle();
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
        	
        	$scope.editNameTitle = function(){
        		$http.post('/parameter/updateNameTitle?updatedBy='+currentAuth.uid , $scope.nameTitleUpdate)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Update Success',
                        text: 'This Name Title is already updated to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.getAllNameTitle();
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



