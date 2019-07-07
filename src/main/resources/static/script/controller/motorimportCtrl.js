'use strict';
angular
    .module('app')
        .controller('motorimportCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.birthday={
        			date:null
        	};
        	
        	
        	
        	$scope.init = function(){
        		$scope.getDataStructure();
        		$scope.getAllBrandData();
        		$scope.getAllColorData();
        		$scope.getAllProvince();
        		$scope.getAllStatus();
        		$scope.getAllType();
//        		$scope.setAllLocationData(); //ดึงข้อมูลจังหวัด อำเภอ ตำบล จาก excel
        	}
        	
        	$scope.getDataStructure = function(){
        		$http.post('/contract/getDataStructure')
		    	.then(function(response){
		    		$scope.contract = response.data;
		    		console.log($scope.contract);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.getAllBrandData = function(){
        		$http.post('/parameter/getAllBrandData')
		    	.then(function(response){
		    		$scope.brands = response.data;
		    	}).catch(function(error){
		    		console.log(error);
		    	})
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
		    		console.log($scope.provinces);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.setAllLocationData = function(){
        		$http.post('/parameter/setAllLocationData')
		    	.then(function(response){
		    		
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.getAllStatus = function(){
        		$http.post('/motorcycle/getAllMotorcycleStatus')
		    	.then(function(response){
		    		$scope.statuss = response.data;
		    		console.log($scope.statuss);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.getAllType = function(){
        		$http.post('/motorcycle/getAllMotorcycleType')
		    	.then(function(response){
		    		$scope.types = response.data;
		    		console.log($scope.types);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.insertMotorcycle = function(){
        		console.log($scope.motorcycle);
        		$http.post('/motorcycle/insertMotorcycle?createdBy='+currentAuth.uid, $scope.motorcycle)
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



