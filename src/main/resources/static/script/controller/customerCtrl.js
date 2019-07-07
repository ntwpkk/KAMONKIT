'use strict';
angular
    .module('app')
        .controller('customerCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
$scope.beforeSearch=true;
        	
        	$scope.init = function(){
        		
        	}
        	
        	$scope.getCustomerByCitizenId = function(ID){
        		$http.post('/customer/getCustomerDataByCitizenId?citizenId='+ID)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.beforeSearch=false;
		    		$scope.customer.birthday = new Date($scope.customer.bdY+"-"+$scope.customer.bdM+"-"+$scope.customer.bdD);
		    		console.log($scope.customer);
		    		
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: 'Not Found',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
        	
        	$scope.getCustomerByname = function(FNAME,LNAME){
        		$http.post('/customer/getCustomerDataByname?fname='+FNAME+'&lname='+LNAME)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.beforeSearch=false;
		    		$scope.customer.birthday = new Date($scope.customer.bdY+"-"+$scope.customer.bdM+"-"+$scope.customer.bdD);
		    		console.log($scope.customer);
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: 'Not Found',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
        	
        	$scope.getCustomerByFirebaseId = function(){
        		$http.post('/customer/getCustomerDataByFirebaseId?fid='+currentAuth.uid)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.customer.birthday = new Date($scope.customer.bdY+"-"+$scope.customer.bdM+"-"+$scope.customer.bdD);
		    		console.log($scope.customer);
		    		
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: 'Not Found',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
	
}]);



