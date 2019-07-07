'use strict';
angular
    .module('app')
        .controller('customeraddressCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.beforeSearch=true;
        	
        	$scope.init = function(){
        		$scope.getAllProvince();
        	}
        	
        	$scope.getCustomerByCitizenId = function(ID){
        		$http.post('/customer/getCustomerByCitizenId?citizenId='+ID)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.beforeSearch=false;
		    		$scope.fillInAddress();
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
        		$http.post('/customer/getCustomerByName?fname='+FNAME+'&lname='+LNAME)
		    	.then(function(response){
	    			$scope.customer = response.data;
		    		$scope.beforeSearch=false;
		    		$scope.fillInAddress();
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
        	
        	$scope.fillInAddress = function(){
        		$scope.cusAddress = $scope.customer.address.detail+" "+$scope.customer.address.subdistrict.name+" "+$scope.customer.address.district.name+" จังหวัด"+$scope.customer.address.province.name+" "+$scope.customer.address.zipcode;
        		$scope.cusWorkplace = $scope.customer.workplace.detail+" "+$scope.customer.workplace.subdistrict.name+" "+$scope.customer.workplace.district.name+" จังหวัด"+$scope.customer.workplace.province.name+" "+$scope.customer.workplace.zipcode;

        		
        		$scope.customer.address = null;
        		$scope.customer.workplace = null;
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
        	
    
        	
        	$scope.updateAddress = function(){
        		$http.post('/customer/updateAddress?updatedBy='+currentAuth.uid, $scope.customer)
		    	.then(function(response){
		    		$scope.beforeSearch = true;
		    		new PNotify({
                        title: 'Update Success',
                        text: 'This customer is already updated to database successfully',
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



