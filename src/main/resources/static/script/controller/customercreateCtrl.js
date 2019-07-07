'use strict';
angular
    .module('app')
        .controller('customercreateCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.brands = [];
        	
        	$scope.init = function(){
        		$scope.getAllProvince();
        		$scope.getAllNameTitleData();
        	}
        	
        	$scope.getAllNameTitleData = function(){
        		$http.post('/parameter/getAllNameTitleData')
		    	.then(function(response){
		    		$scope.nameTitles = response.data;
		    		console.log($scope.nameTitles);
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
        	
        	$scope.selectProvinceW = function(){
        		$scope.subw=null;
        		$http.post('/parameter/getAllDistrict?provinceId='+$scope.customer.workplace.province.provinceId)
		    	.then(function(response){
		    		$scope.districtw = response.data;
		    		console.log($scope.districts);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.selectDistrictW = function(){
        		$http.post('/parameter/getAllSubdistrict?districtId='+$scope.customer.workplace.district.districtId)
		    	.then(function(response){
		    		$scope.subw = response.data;
		    		console.log($scope.subs);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.selectProvinceA = function(){
        		$scope.suba=null;
        		$http.post('/parameter/getAllDistrict?provinceId='+$scope.customer.address.province.provinceId)
		    	.then(function(response){
		    		$scope.districta = response.data;
		    		console.log($scope.districts);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.selectDistrictA = function(){
        		$http.post('/parameter/getAllSubdistrict?districtId='+$scope.customer.address.district.districtId)
		    	.then(function(response){
		    		$scope.suba = response.data;
		    		console.log($scope.subs);
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.testDate = function(){
        		console.log($scope.customer.birthday.getDate()+ "  "+$scope.customer.birthday.getMonth()+"  "+$scope.customer.birthday.getYear());
        		$http.post('/customer/test',$scope.customer.birthday)
		    	.then(function(response){
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.insertCustomer = function(){
        		$http.post('/customer/insertCustomer?createdBy='+currentAuth.uid, $scope.customer)
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



