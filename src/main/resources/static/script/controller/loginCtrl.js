
'use strict';
     
angular
.module('app')
	.controller('loginCtrl', [ '$scope', '$http','$rootScope','$state','Auth','$window',function($scope,$http,$rootScope,$state,Auth,$window) {
	  
	  
	  
	  
	  
	  $scope.emails="";
	  $scope.password="";
	  $scope.auth =Auth;

	  $scope.login = function(){
		  console.log("login");
		  $scope.auth.$signInWithEmailAndPassword($scope.emails+"@kamonkit.com",$scope.password).then(function(){
//			  $state.go('admin.dashboard');
			  
		  }).catch(e => {
			  alert(e.message);
		  });
		  
	  }
	  
	  $scope.logout = function(){
//		  firebase.auth().signOut();
		  $rootScope.user = "EXIT";
		  $scope.showLogout = false;
	  }
	  
	  
	  Auth.$onAuthStateChanged(function(firebaseUser) {
		  if(firebaseUser){
			  console.log($rootScope.firebaseUser);
//			  $state.go('admin.dashboard');
			  setTimeout(function(){ $window.location.reload(); }, 500);
		  }
		  else{
			  console.log("not Login");
		  }
	      
	    });
	  
	  
	  
	  
	  
	  
  }]);

  
