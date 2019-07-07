'use strict';
angular
    .module('app')
        .controller('motorcolorCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.beforeSearch = true;
        	$scope.init = function(){
        		$scope.getAllProvince();
        		$scope.getAllColorData();
        	}
        	
        	$scope.getAllColorData = function(){
        		$http.post('/parameter/getAllColorData')
		    	.then(function(response){
		    		$scope.colors = response.data;
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.getAllProvince = function(){
        		$http.post('/parameter/getAllProvince')
		    	.then(function(response){
		    		$scope.provinces = response.data;
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.getMotorcycleByRegistNumber = function(){
        		$http.post('/motorcycle/getMotorcycleByRegistNumber?number='+$scope.registrationNumber+'&province='+$scope.registrationProvince.provinceId)
		    	.then(function(response){
		    		$scope.motorcycle = response.data;
		    		$scope.beforeSearch=false;
		    		$scope.fillInData();
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
        	
        	$scope.getMotorcycleByEngineNumber = function(){
        		$http.post('/motorcycle/getMotorcycleByEngineNumber?number='+$scope.engineNumber)
		    	.then(function(response){
		    		$scope.motorcycle = response.data;
		    		$scope.beforeSearch=false;
		    		console.log($scope.motorcycle);
		    		$scope.fillInData();
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
        	
        	$scope.getMotorcycleByChassisNumber = function(){
        		console.log($scope.chassisNumber);
        		$http.post('/motorcycle/getMotorcycleByChassisNumber?number='+$scope.chassisNumber)
		    	.then(function(response){
		    		$scope.motorcycle = response.data;
		    		$scope.beforeSearch=false;
		    		$scope.fillInData();
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
        	
        	$scope.fillInData = function(){
        		$scope.registNumber = $scope.motorcycle.registrationNumber;
	    		$scope.registProvince = $scope.motorcycle.registrationProvince.name;
	    		$scope.chassisNumber = $scope.motorcycle.chassisNumber;
	    		$scope.engineNumber = $scope.motorcycle.engineNumber;
	    		$scope.color = $scope.motorcycle.color.name;
	    		$scope.motorcycle.color = null;
        	}
        	
        	$scope.updateColor = function(){
        		console.log($scope.motorcycle);
        		$http.post('/motorcycle/updateColor?updatedBy='+currentAuth.uid, $scope.motorcycle)
		    	.then(function(response){
		    		$scope.beforeSearch = true;
		    		new PNotify({
                        title: 'Success',
                        text: 'This motorcycle is already inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    	}).catch(function(error){
		    		console.log(error);
		    		new PNotify({
                        title: 'Error '+error.status,
                        text: 'Something wrong',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
		    	})
        	}
        	
        	
	
}]);



