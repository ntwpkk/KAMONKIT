'use strict';
angular
    .module('app')
        .controller('customertelephoneCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.beforeSearch=true;
        	
        	$scope.init = function(){
        		
        	}
        	
        	$scope.getCustomerByCitizenId = function(ID){
        		$http.post('/customer/getCustomerByCitizenId?citizenId='+ID)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.beforeSearch=false;
		    		console.log($scope.customer);
		    		$scope.telephone = $scope.customer.telephone;
		    		$scope.customer.telephone=null;
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
        		$http.post('/customer/getCustomerByName?fname='+FNAME+'&lname='+LNAME)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.beforeSearch=false;
		    		console.log($scope.customer);
		    		$scope.telephone = $scope.customer.telephone;
		    		$scope.customer.telephone=null;
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
        	
        	$scope.updateTelephone = function(){
        		$http.post('/customer/updateName?updatedBy='+currentAuth.uid, $scope.customer)
		    	.then(function(response){
		    		$scope.beforeSearch = true;
		    		new PNotify({
                        title: 'Create Success',
                        text: 'This customer is already inserted to database successfully',
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



