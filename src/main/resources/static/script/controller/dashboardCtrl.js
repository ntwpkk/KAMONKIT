'use strict';
angular
    .module('app')
        .controller('dashboardCtrl', [ '$scope', '$http','currentAuth' , 'Auth' ,function($scope,$http,currentAuth,Auth) {
        	
        	
        	$scope.saveSkill = function(skill){
        		$http.post('/skpos/saveSkill',skill)
		    	.then(function(response){
		    		$scope.showAll();
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.updateSkill = function(skill){
        		$http.post('/skpos/updateSkill',skill)
		    	.then(function(response){
		    		$scope.showAll();
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.deleteSkill = function(skillId){
        		$http.post('/skpos/deleteSkill?skillId='+skillId)
		    	.then(function(response){
		    		$scope.showAll();
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.savePosition = function(position){
        		$http.post('/skpos/savePosition',position)
		    	.then(function(response){
		    		$scope.showAll();
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.updatePosition = function(position){
        		$http.post('/skpos/updatePosition',position)
		    	.then(function(response){
		    		$scope.showAll();
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
        	
        	$scope.deletePosition = function(positionId){
        		$http.post('/skpos/deletePosition?positionId='+positionId)
		    	.then(function(response){
		    		$scope.showAll();
		    	}).catch(function(error){
		    		console.log(error);
		    	})
        	}
	
	
	
}]);



