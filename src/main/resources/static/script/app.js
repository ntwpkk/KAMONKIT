'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description
 * # app
 *
 * Main module of the application.
 */

angular
  .module('app', ['ui.router','oc.lazyLoad', 'ngStorage','firebase','ngAnimate', 'ngSanitize', 'ui.bootstrap']).constant()
  .run(['$http', '$rootScope', '$q',
        function($http, $rootScope, $q) {
	  
	  console.log('xxx');
	  
//	  $rootScope.initParams = function(){
//		  var promises = [];
//		  promises.push( $http.get(_params.global.contextPath + '/api/data/pricing?size=100&sort=maxUser,asc' ).then(function(response){
//			  $rootScope.priceList = response.data._embedded.pricing;
//			  window.console && console.log('$rootScope.priceList  >>> ');
//			  window.console && console.log($rootScope.priceList);
//		  }) );	
//		  
//		  return $q.all(promises);
//	  };
	  
	  
	  
    }]);
    
	

