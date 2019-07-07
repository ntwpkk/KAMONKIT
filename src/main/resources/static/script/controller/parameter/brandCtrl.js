'use strict';
angular
    .module('app')
        .controller('brandCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	$scope.showInstallmentTable = false;
        	
        	$scope.init = function(){
        		$scope.getAllBrand();
        	}
        	
        	
        	$scope.getAllBrand = function(){
        		$http.post('/parameter/getAllBrand')
		    	.then(function(response){
		    		$scope.brands = response.data;
		    		console.log($scope.brands);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.selectBrand = function(brand){
        		$scope.showModel=true;
        		$scope.brandId =brand;
        		$http.post('/parameter/getAllModelById?brandId='+brand)
		    	.then(function(response){
		    		$scope.models = response.data;
		    		console.log($scope.models);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.clickEditBrand = function(brand){
        		$scope.insertBrand = false;
        		$scope.brandUpdate = brand;
        	}
        	$scope.clickEditModel = function(model){
        		$scope.insertModel = false;
        		$scope.modelUpdate = model;
        	}
        	
        	$scope.saveBrand = function(){
        		$http.post('/parameter/insertBrand?createdBy='+currentAuth.uid , $scope.brandInsert)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Create Success',
                        text: 'This brand is already inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.getAllBrand();
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
        	
        	$scope.editBrand = function(){
        		$http.post('/parameter/updateBrand?updatedBy='+currentAuth.uid , $scope.brandUpdate)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Update Success',
                        text: 'This brand is already updated to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.getAllBrand();
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
        	
        	$scope.saveModel = function(){
        		$http.post('/parameter/insertModel?createdBy='+currentAuth.uid+'&brandId='+$scope.brandId , $scope.modelInsert)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Create Success',
                        text: 'This model is already inserted to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.selectBrand($scope.brandId);
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
        	
        	$scope.editModel = function(){
        		$http.post('/parameter/updateModel?updatedBy='+currentAuth.uid, $scope.modelUpdate)
		    	.then(function(response){
		    		new PNotify({
                        title: 'Update Success',
                        text: 'This model is already updated to database successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
		    		$scope.selectBrand($scope.brandId);
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



