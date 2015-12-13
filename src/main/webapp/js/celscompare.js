/**
 * 
 */
// Encoding: ISO-8859-1

$(function() {
    function reposition() {
        var modal = $(this),
            dialog = $("#alert_dialog");
        modal.css('display', 'block');
        
        // Dividing by two centers the modal exactly, but dividing by three 
        // or four works better for larger screens.
        dialog.css("margin-top", Math.max(0, ($(window).height() - dialog.height()) / 2));
    }
    // Reposition when a modal is shown
    $('.modal').on('show.bs.modal', reposition);
    // Reposition when the window is resized
    $(window).on('resize', function() {
        $('.modal:visible').each(reposition);
    });
});






var celsCompareApp = angular.module('celsCompareApp', [])

var errorCallback = function($scope){
	return function(response){
		$scope.alertaMensagem='Erro status '+response.status;
		$("#alert").modal('show');
	}
}

celsCompareApp.service('celscompareService',['$http',function($http){
	
	this.alertMessage = function($scope,message){
		$scope.alertaMensagem=message;
		$("#alert").modal('show');
		
	}
	this.findAll = function(callBack,error){
		$http.post("/findAll",{})
		        .then(function (response) {
		        	//$("#aguarde").modal('hide');
		        	callBack(response.data);
		         },function(response){
		        	 //$("#aguarde").modal('hide');
		        	 error(response);
		         });
		
	}
	this.findAllOrderByFavoritos = function(callBack,error){
		$http.post("/findAllOrderByFavorito",{})
		        .then(function (response) {
		        	callBack(response.data);
		         },function(response){
		        	 error(response);
		         });
		
	}
	this.buscaComparativos = function(celular1,celular2,callBack,error){
		
		$http.post("/compare/"+celular1+"/"+celular2,{})
		        .then(function (response) {
		        	$("#aguarde").modal('hide');
		    		
		        	callBack(response.data);
		         },function(response){
		        	 $("#aguarde").modal('hide');
		        	 error(response);
		         });
		
	}
	this.addFavorito = function(celular,callBack,error){
		
		$http.post("/addFavorito/"+celular,{})
		        .then(function (response) {
		        	$("#aguarde").modal('hide');
		        	callBack(response.data);
		         },function(response){
		        	 $("#aguarde").modal('hide');
		        	 error(response);
		         });
		
	}
	
	
	
}]);

celsCompareApp.controller('celularList',['$scope', 'celscompareService', function ($scope,celscompareService) {
	$scope.celularesComparacao=[];
	celscompareService.findAll(function(response){
		 $scope.celulares =  response;  
		   
	  },errorCallback($scope));
	  $scope.addCompare = function(compareRow){
		    if($scope.celularesComparacao.length > 1 || $scope.celularesComparacao == null){
		    	$scope.celularesComparacao = [];
		    	document.getElementById('compare').disabled='disabled';
		    }
		    if($scope.celularesComparacao.length == 1){
				if($scope.celularesComparacao[0].idCelular == compareRow.idCelular){
					celscompareService.alertMessage($scope,"O celular já foi selecionado para comparação");
                    return;
				}
			}
		    $scope.celularesComparacao.push(compareRow);
			if($scope.celularesComparacao.length == 2){
				document.getElementById('compare').disabled='';
			}
	  }
	  $scope.favorito = function(idCelular){
		  celscompareService.addFavorito(idCelular,function(data){
			  document.getElementById('formFavorito#'+ idCelular).submit();
		  },errorCallback($scope))
	  }
	  $scope.compare = function(){
		   celscompareService.buscaComparativos($scope.celularesComparacao[0].idCelular,
				  $scope.celularesComparacao[1].idCelular, function(response){
			  $scope.comparativo =   response;
			  $("#comparativo").modal('show');
		  },errorCallback($scope))
	  }
	
	}]);
celsCompareApp.controller('favoritoList',['$scope', 'celscompareService', function ($scope,celscompareService) {
	  celscompareService.findAllOrderByFavoritos(function(response){
		 $scope.favoritos =  response;  
		   
	  });
	 
	
	}]);

