/**
 * 
 */

var celsCompareApp = angular.module('celsCompareApp', []);



celsCompareApp.service('celscompareService',['$http','$location',function($http,$location){
	this.findAll = function(callBack){
		$http.post("/findAll",{})
		        .then(function (response) {
		        	callBack(response.data);
		         },function(response){
		        	 alert('Erro status'+response.status);
		         });
		
	}
	this.findAllOrderByFavoritos = function(callBack){
		$http.post("/findAllOrderByFavorito",{})
		        .then(function (response) {
		        	callBack(response.data);
		         },function(response){
		        	 alert('Erro status'+response.status);
		         });
		
	}
	this.buscaComparativos = function(celular1,celular2,callBack){
		$http.post("/compare/"+celular1+"/"+celular2,{})
		        .then(function (response) {
		        	callBack(response.data);
		         },function(response){
		        	 alert('Erro status'+response.status);
		         });
		
	}
	this.changeLocation = function(url){
		$location.path(url);
	}
	
}]);

celsCompareApp.controller('celularList',['$scope', 'celscompareService', function ($scope,celscompareService) {
	$scope.celularesComparacao=[];
	celscompareService.findAll(function(response){
		 $scope.celulares =  response;  
		   
	  });
	  $scope.addCompare = function(compareRow){
		    if($scope.celularesComparacao.length > 1){
		    	$scope.celularesComparacao = [];
		    	document.getElementById('compare').disabled='disabled';
		    }
			$scope.celularesComparacao.push(compareRow);
			if($scope.celularesComparacao.length == 2){
				document.getElementById('compare').disabled='';
			}
	  }
	  $scope.favorito = function(idCelular){
		  document.getElementById('formFavorito#'+ idCelular).submit();
	  }
	  $scope.compare = function(){
		  celscompareService.buscaComparativos($scope.celularesComparacao[0].idCelular,
				  $scope.celularesComparacao[1].idCelular, function(response){
			  $scope.comparativo =   response;
			  $('#comparativo').modal('show');
		  })
	  }
	
	}]);
celsCompareApp.controller('favoritoList',['$scope', 'celscompareService', function ($scope,celscompareService) {
	  celscompareService.findAllOrderByFavoritos(function(response){
		 $scope.favoritos =  response;  
		   
	  });
	 
	
	}]);

