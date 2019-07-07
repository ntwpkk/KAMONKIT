'use strict';
angular
    .module('app')
        .controller('contractcreateCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.brands = [];
        	$scope.beforeSearchMotor=true;
        	$scope.beforeSearchCust=true;
        	$scope.beforeSearchGua=true;
        	$scope.numberofInstallments=[6,12,15,18,20,24];
        	$scope.code2 = ("" + (new Date().getFullYear() + 543)).substring(2,4);
        	console.log(new Date().getYear() + 543);
        	console.log($scope.code2);
        	
        	$scope.init = function(){
        		$scope.getDataStructure();
        		$scope.getAllBrandData();
        		$scope.getAllColorData();
        		$scope.getAllProvince();
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
        	
        	$scope.getDataStructure = function(){
        		$http.post('/contract/getDataStructure')
		    	.then(function(response){
		    		$scope.contract = response.data;
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
        	
        	$scope.getAllProvince = function(){
        		$http.post('/parameter/getAllProvince')
		    	.then(function(response){
		    		$scope.provinces = response.data;
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
	
        	$scope.calculate = function(){
        		$scope.contract.motorcycle = $scope.motorcycle;
        		$scope.contract.customer = $scope.customer;
        		$scope.contract.guarantor = $scope.guarantor;
        		$http.post('/contract/calculate',$scope.contract)
		    	.then(function(response){
		    		$scope.installments = response.data;
//		    		$scope.contract.monthlyPay = $scope.installments[0];
		    		console.log($scope.installments[0]);
		    		console.log($scope.installments);
	        		$scope.showInstallmentTable=true;
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.insertContract = function(){
        		$scope.contract.installment = $scope.installments;
        		$scope.contract.code = $scope.code1 + "-" + $scope.code2 + "-" + $scope.code3;
        		console.log($scope.contract);
        		$http.post('/contract/insertContract?createdBy=' + currentAuth.uid, $scope.contract)
		    	.then(function(response){
		    		$scope.showReport=true;
		    		new PNotify({
                        title: 'Success',
                        text: 'This contract is inserted to database successfully',
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
	
        	
        	$scope.indexStep = 1;
        	$scope.nextBtn = function(){
        		$scope.indexStep+=1;
        		if($scope.indexStep==2){
        			
        			document.getElementById("step1").classList.remove("selected");
        			document.getElementById("step2").classList.remove("disabled");
        			document.getElementById("step2").classList.remove("done");
        			document.getElementById("step1").classList.add("done");
        			document.getElementById("step2").classList.add("selected");

        			document.getElementById("step-1").style.display="none";
        			document.getElementById("step-2").style.display="block";
        			
        			$scope.showPrev=true;
        		}
        		else if($scope.indexStep==3){
        			
        			document.getElementById("step2").classList.remove("selected");
        			document.getElementById("step3").classList.remove("disabled");
        			document.getElementById("step3").classList.remove("done");
        			document.getElementById("step2").classList.add("done");
        			document.getElementById("step3").classList.add("selected");

        			document.getElementById("step-2").style.display="none";
        			document.getElementById("step-3").style.display="block";
        		}
        		else if($scope.indexStep==4){
        			
        			document.getElementById("step3").classList.remove("selected");
        			document.getElementById("step4").classList.remove("disabled");
        			document.getElementById("step4").classList.remove("done");
        			document.getElementById("step3").classList.add("done");
        			document.getElementById("step4").classList.add("selected");

        			document.getElementById("step-3").style.display="none";
        			document.getElementById("step-4").style.display="block";
        			
        			$scope.showFinish=true;
        			$scope.showNext=false;
        		}
        	}
        	
        	
        	$scope.showNext=true;
        	$scope.showPrev=false;
        	$scope.showFinish=false;
        	$scope.prevBtn = function(){
        		$scope.indexStep-=1;
        		if($scope.indexStep==1){
        			
        			document.getElementById("step2").classList.remove("selected");
        			document.getElementById("step1").classList.remove("done");
        			document.getElementById("step2").classList.add("done");
        			document.getElementById("step1").classList.add("selected");

        			document.getElementById("step-2").style.display="none";
        			document.getElementById("step-1").style.display="block";
        			
        			$scope.showPrev=false;
        		}
        		else if($scope.indexStep==2){
        			
        			document.getElementById("step3").classList.remove("selected");
        			document.getElementById("step2").classList.remove("done");
        			document.getElementById("step3").classList.add("done");
        			document.getElementById("step2").classList.add("selected");

        			document.getElementById("step-3").style.display="none";
        			document.getElementById("step-2").style.display="block";
        		}
        		else if($scope.indexStep==3){
        			
        			document.getElementById("step4").classList.remove("selected");
        			document.getElementById("step3").classList.remove("done");
        			document.getElementById("step4").classList.add("done");
        			document.getElementById("step3").classList.add("selected");

        			document.getElementById("step-4").style.display="none";
        			document.getElementById("step-3").style.display="block";
        			
        			$scope.showNext=true;
                	$scope.showFinish=false;
        		}
        		  
        		
        	}
        	
        	
        	
        	$scope.getMotorcycleByRegistNumber = function(){
        		$http.post('/motorcycle/getMotorcycleByRegistNumber?number='+$scope.registrationNumber+'&province='+$scope.registrationProvince.provinceId)
		    	.then(function(response){
		    		$scope.motorcycle = response.data;
		    		$scope.beforeSearchMotor=false;
		    		$scope.code1 = $scope.checkType($scope.motorcycle.type);
		    		$scope.contract.downPayment = $scope.motorcycle.downPayment;
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
		    		$scope.beforeSearchMotor=false;
		    		console.log($scope.motorcycle);
		    		$scope.checkType($scope.motorcycle.type);
		    		$scope.contract.downPayment = $scope.motorcycle.downPayment;
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
		    		$scope.beforeSearchMotor=false;
		    		$scope.checkType($scope.motorcycle.type);
		    		$scope.contract.downPayment = $scope.motorcycle.downPayment;
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
        	
        	$scope.getCustomerByCitizenId = function(i,ID){
        		$http.post('/customer/getCustomerByCitizenId?citizenId='+ID)
		    	.then(function(response){
		    		if(i==0){
		    			$scope.customer = response.data;
			    		$scope.customer.birthday = new Date($scope.customer.bdY+"-"+$scope.customer.bdM+"-"+$scope.customer.bdD);
			    		$scope.beforeSearchCust=false;
		    		}
		    		else if(i==1){
		    			$scope.guarantor = response.data;
			    		$scope.guarantor.birthday = new Date($scope.guarantor.bdY+"-"+$scope.guarantor.bdM+"-"+$scope.guarantor.bdD);
			    		$scope.beforeSearchGua=false;
		    		}
		    		
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
        	
        	$scope.checkType = function(type){
        		console.log(type);
        		if(type=="NEW")
        			$scope.code1 =  "01";
        		else if(type=="USED")
        			$scope.code1 =  "02";
        		console.log($scope.code1);
        	}
	
}]);



