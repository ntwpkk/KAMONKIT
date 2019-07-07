'use strict';
angular
    .module('app')
        .controller('contractCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.beforeSearch = true;
        	
        	$scope.init = function(){
        		
        	}
        	
        	$scope.getContractDataByCode = function(){
        		$http.post('/contract/getContractDataByCode?code='+$scope.contractcode)
		    	.then(function(response){
		    		$scope.select = response.data;
		    		console.log($scope.select);
		    		$scope.beforeSearch = false;
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
	
}]);



