'use strict';
angular
    .module('app')
        .controller('locationCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	$scope.showInstallmentTable = false;
        	$scope.birthday={
        			date:null
        	};
        	
        	$scope.init = function(){
        		$scope.getAllProvince();
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
        	
        	$scope.selectProvince = function(province){
        		$scope.showDistrict=true;
        		$scope.showSub=false;
        		$http.post('/parameter/getAllDistrict?provinceId='+province.provinceId)
		    	.then(function(response){
		    		$scope.districts = response.data;
		    		console.log($scope.districts);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.selectDistrict = function(district){
        		$scope.showSub=true;
        		$http.post('/parameter/getAllSubdistrict?districtId='+district.districtId)
		    	.then(function(response){
		    		$scope.subs = response.data;
		    		console.log($scope.subs);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	
	
}]);



