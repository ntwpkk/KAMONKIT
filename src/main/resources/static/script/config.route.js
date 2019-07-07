angular.module('app')
	.run(["$rootScope", "$state", 'Auth','$window','$http', function($rootScope, $state,Auth,$window,$http) {
		  $rootScope.$on("$stateChangeError", function(event, toState, toParams, fromState, fromParams, error) {
		    // We can catch the error thrown when the $requireSignIn promise is rejected
		    // and redirect the user back to the home page
		    if (error === "AUTH_REQUIRED") {
		      $state.go("login");
		    }
		  });
		  
		 Auth.$onAuthStateChanged(function(firebaseUser) {
			  if(firebaseUser){
				  $rootScope.firebaseUser = firebaseUser;
				  console.log($rootScope.firebaseUser);
				  $http.post('/employee/checkRoleUser?fid='+firebaseUser.uid)
			    	.then(function(response){
			    		console.log(response.data);
			    		$rootScope.role = response.data
			    		switch($rootScope.role){
			    		case "ADMINISTRATOR" : $state.go("admin.dashboard");break;
			    		case "ACCOUNTANT" : $state.go("admin.dashboard");break;
			    		case "INVESTIGATOR" : $state.go("admin.dashboard");break;
			    		default : $state.go("admin.customer2");break;
			    		}
			    	}).catch(function(error){
			    		console.log(error);
			    	})
			  }
			  else{
				  $state.go("login");
			  }
		      
		    });
		  
		  $rootScope.logout = function(){
			  console.log(Auth);
			  Auth.$signOut();
			  $window.location.reload();
		  }
	}])
	
    .config(['$stateProvider','$urlRouterProvider','$injector',function ($stateProvider, $urlRouterProvider,$injector) {
    	$urlRouterProvider.otherwise( function($injector) {
        	var $state = $injector.get("$state");
//        	if($rootScope.role=="ADMINISTRATOR"||$rootScope.role=="ACCOUNTANT"||$rootScope.role=="INVESTIGATOR"){
//        		$state.go('admin.dashboard');
//        	}
//        	else{
//        		$state.go("customer.home");
//        	}
        	$state.go("login");
        });
	
        $stateProvider
        	.state('login', {
            url: '/login',
            templateUrl: './view/app/login.html',
            controller:'loginCtrl'
        	})
            .state('admin', {
            	abstract: true,
            	url:'/admin',
                templateUrl: './view/layout/layout.html',
                controller: 'employeeCtrl',
                resolve: {
                    // controller will not be loaded until $waitForSignIn resolves
                    // Auth refers to our $firebaseAuth wrapper in the factory below
                    "currentAuth": ["Auth", function(Auth) {
                      // $waitForSignIn returns a promise so the resolve waits for it to complete
                      return Auth.$requireSignIn();
                    }]
//                    "role":["$http" , "currentAuth" , function ($http,currentAuth) {
//                        return $http.post('/user/checkRoleUser?firebaseId='+currentAuth.uid)
//    			    	.then(function(response){
//    			    		console.log(response.data);
//    			    		return response.data
//    			    	}).catch(function(error){
//    			    		console.log(error);
//    			    	})
//                    }]
                }
            })
            .state('admin.dashboard', {
                url: '/dashboard',
                templateUrl: './view/app/dashboard.html',
                controller: 'dashboardCtrl'
            
            })
            .state('admin.contract', {
                url: '/contract',
                templateUrl: './view/app/admin/contract.html',
                controller: 'contractCtrl'
            
            })
            .state('admin.contractcreate', {
                url: '/contract/create',
                templateUrl: './view/app/admin/contractcreate.html',
                controller: 'contractcreateCtrl'
            
            })
            .state('admin.payinstallment', {
                url: '/contract/pay',
                templateUrl: './view/app/admin/payinstallment.html',
                controller: 'payinstallmentCtrl'
            
            })
            .state('admin.motorcycle', {
                url: '/motorcycle',
                templateUrl: './view/app/admin/motorcycle.html',
                controller: 'motorCtrl'
                
            })
            .state('admin.motorcyclechangechassis', {
                url: '/motorcycle/chassis',
                templateUrl: './view/app/admin/motorcycleChangechassis.html',
                controller: 'motorchassisCtrl'
                
            })
            .state('admin.motorcyclechangecolor', {
                url: '/motorcycle/color',
                templateUrl: './view/app/admin/motorcycleChangecolor.html',
                controller: 'motorcolorCtrl'
                
            })
            .state('admin.motorcyclechangeregistration', {
                url: '/motorcycle/registration',
                templateUrl: './view/app/admin/motorcycleChangeregistration.html',
                controller: 'motorregistrationCtrl'
                
            })
            .state('admin.motorcyclechangeengine', {
                url: '/motorcycle/engine',
                templateUrl: './view/app/admin/motorcycleChangeengine.html',
                controller: 'motorengineCtrl'
                
            })
            .state('admin.motorcycleimport', {
                url: '/motorcycle/import',
                templateUrl: './view/app/admin/motorcycleimport.html',
                controller: 'motorimportCtrl'
            
            })
            .state('admin.customer', {
                url: '/customer',
                templateUrl: './view/app/admin/customer.html',
                controller: 'customerCtrl'
            
            })
            .state('admin.customer2', {
                url: '/customerData',
                templateUrl: './view/app/admin/customer2.html',
                controller: 'customerCtrl'
            
            })
            .state('admin.customerchangeaddress', {
                url: '/customer/address',
                templateUrl: './view/app/admin/customerChangeaddress.html',
                controller: 'customeraddressCtrl'
            
            })
            .state('admin.customerchangename', {
                url: '/customer/name',
                templateUrl: './view/app/admin/customerChangename.html',
                controller: 'customernameCtrl'
            
            })
            .state('admin.customerchangetelephone', {
                url: '/customer/telephone',
                templateUrl: './view/app/admin/customerChangetelephone.html',
                controller: 'customertelephoneCtrl'
            
            })
             .state('admin.customercreate', {
                url: '/customer/create',
                templateUrl: './view/app/admin/customercreate.html',
                controller: 'customercreateCtrl'
            
            })
            .state('admin.employee', {
                url: '/employee',
                templateUrl: './view/app/admin/employee.html',
                controller: 'employee2Ctrl'
            
            })
            .state('admin.employeecreate', {
                url: '/employee/create',
                templateUrl: './view/app/admin/employeecreate.html',
                controller: 'employeecreateCtrl'
            
            })
            .state('admin.location', {
                url: '/location',
                templateUrl: './view/app/admin/parameter/location.html',
                controller: 'locationCtrl'
            
            })
            .state('admin.brand', {
                url: '/brand',
                templateUrl: './view/app/admin/parameter/brand.html',
                controller: 'brandCtrl'
            
            })
            .state('admin.color', {
                url: '/color',
                templateUrl: './view/app/admin/parameter/color.html',
                controller: 'colorCtrl'
            
            })
            .state('admin.nameTitle', {
                url: '/nameTitle',
                templateUrl: './view/app/admin/parameter/nameTitle.html',
                controller: 'nameTitleCtrl'
            
            })
            
            
//             .state('customer', {
//            	abstract: true,
//            	url:'/customer',
//                templateUrl: './view/layout/layoutCustomer.html'
//             })
//            .state('customer.home', {
//                url: '/home',
//                templateUrl: './view/app/customer/home.html',
////                controller: 'performanceCtrl',
//                resolve: {
//                    // controller will not be loaded until $waitForSignIn resolves
//                    // Auth refers to our $firebaseAuth wrapper in the factory below
//                    "currentAuth": ["Auth", function(Auth) {
//                      // $waitForSignIn returns a promise so the resolve waits for it to complete
//                      return Auth.$requireSignIn();
//                    }]
//                  }
//            })
            
            
        
        
    }])
    .factory("Auth", ["$firebaseAuth",
	  function($firebaseAuth) {
	    return $firebaseAuth();
	  }
	])
//	.service('role',['$rootScope', function($rootScope) {
//		console.log($rootScope.role);
//		if(){
//			
//		}
//		else{
//			
//		}
//	    return "admin";
//	}]);
	.controller("roleCtrl", ["$scope","$rootScope",
	  function($scope,$rootScope) {
		console.log(":::::role :::::");
		console.log($rootScope.role);
		$scope.role = $rootScope.role;
	  }
	])
	
	
	.controller('employeeCtrl', [ '$scope', '$http','currentAuth' , 'Auth' , '$rootScope',function($scope,$http,currentAuth,Auth,$rootScope) {
        	
        	
        	$scope.init = function(){
        		$scope.getAllEmployee();
        	}
        	
        	$scope.getAllEmployee = function(){
        		$http.post('/employee/getEmployeeData?fid='+currentAuth.uid)
		    	.then(function(response){
		    		$scope.user = response.data;
		    		console.log($scope.user);
		    		$scope.role = $rootScope.role;
		    		console.log($scope.role);
		    	}).catch(function(error){
		    		console.log(error);
		    		$http.post('/customer/getCustomerName?fid='+currentAuth.uid)
			    	.then(function(response){
			    		$scope.user = response.data;
			    		console.log($scope.user);
			    		$scope.role = null;
			    		console.log($scope.role);
			    	}).catch(function(error){
			    		console.log(error);
			    		
			    	})
		    		
		    	})
        	}
        	
        	
        	$scope.checkAccountant = function(){
        		if($scope.role=="ADMINISTRATOR" || $scope.role=="ACCOUNTANT"){
        			return true;
        		}
        		else{
        			return false;
        		}
        	}
        	
        	$scope.checkAdmin = function(){
        		if($scope.role=="ADMINISTRATOR"){
        			return true;
        		}
        		else{
        			return false;
        		}
        	}
        	
        	$scope.checkInvestigator = function(){
        		if($scope.role=="ADMINISTRATOR" || $scope.role=="INVESTIGATOR"){
        			return true;
        		}
        		else{
        			return false;
        		}
        	}
        	
        	$scope.checkCustomer = function(){
        		if($scope.role==null){
        			return true;
        		}
        		else{
        			return false;
        		}
        	}
        	
        	$scope.newPassword =" ";
        	
        	$scope.changePassword = function(){
    			currentAuth.updatePassword($scope.newPassword).then(function() {
    				  alert("Update Password Successful");
    				}).catch(function(error) {
    					if(error.code=="auth/weak-password"){
    						
    						alert("weak-password\nPlease try again");
    					}
    					if(error.code=="auth/requires-recent-login"){
    						var confirmLogout = confirm(error.message+"\nDo you want to log out");
    						if(confirmLogout==true){
    							Auth.$signOut().then(function() {
    								console.log("log out successful");
    							    $state.reload();
    						    }).catch(function(error) {
    							    console.log(error.message);
    						    });
    					    }
    					}
    					
    					
    				});
    		}
	
	
	
}]);

	
		
	
	
	
    
    

