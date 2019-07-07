'use strict';
angular
    .module('app')
        .controller('payinstallmentCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.beforeSearch = true;
        	$scope.init = function(){
        		
        	}
        	
        	
        	$scope.getContractByCode = function(){
        		$http.post('/contract/getContractByCodeforPay?code='+$scope.contractcode)
		    	.then(function(response){
		    		$scope.contract = response.data;
		    		console.log($scope.contract);
		    		$scope.beforeSearch = false;
		    		$scope.cname = $scope.contract.customer.fname + " " + $scope.contract.customer.lname;
		    		$scope.gname = $scope.contract.guarantor.fname + " " + $scope.contract.guarantor.lname;
		    		if($scope.contract.currentMonth==100){
		    			new PNotify({
	                        title: 'Notice',
	                        text: 'This Installment is the last month',
	                        type: 'notice',
	                        styling: 'bootstrap3'
	                    });
		    		}
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.payInstallment = function(){
        		$http.post('/contract/payInstallment?createdBy=' + currentAuth.uid, $scope.contract)
		    	.then(function(response){
		    		$scope.beforeSearch = true;
		    		new PNotify({
                        title: 'Success',
                        text: 'This Installment is inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: 'Something Wrong',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
        	
        	
}]);



