'use strict';
angular
    .module('app')
        .controller('motorCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	$scope.brands = [];
        	
        	$scope.init = function(){
        		$scope.getDataStructure();
        		$scope.getAllBrandData();
        		$scope.getAllColorData();
        		$scope.getAllProvince();
        		$scope.getAllStatusMotorcycleData();
        	}
        	
        	$scope.getAllStatusMotorcycleData = function(){
        		$http.post('/parameter/getAllStatusMotorcycleData')
		    	.then(function(response){
		    		$scope.statuss = response.data;
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
        	
        	$scope.getDataStructure = function(){
        		$http.post('/motorcycle/getDataStructure')
		    	.then(function(response){
		    		$scope.motorcycle = response.data;
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
	
}]);



