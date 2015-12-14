/**
 * 
 */
// Encoding: ISO-8859-1

jQuery(function() {
    function reposition() {
        var modal =  jQuery(this),
            dialog = jQuery("#alert_dialog");
        modal.css('display', 'block');
        
        // Dividing by two centers the modal exactly, but dividing by three 
        // or four works better for larger screens.
        dialog.css("margin-top", Math.max(0, (jQuery(window).height() - dialog.height()) / 2));
    }
    // Reposition when a modal is shown
    jQuery('.modal').on('show.bs.modal', reposition);
    // Reposition when the window is resized
    jQuery(window).on('resize', function() {
    	jQuery('.modal:visible').each(reposition);
    });
});



function formatMelhor(){
    jQuery( "#table_comparativo tr td:contains('Melhor')" ).css("color", "red");
};
function celularCount(celulares){
	var count = 0;
	var index
	for	(index = 0; index < celulares.length; index++) {
	    celular = celulares[index];
	    if(celular != null){
	    	count++;
	    }
	}
	return count;
	
}



var celsCompareApp = angular.module('celsCompareApp', [])

var errorCallback = function($scope){
	return function(response){
		$scope.alertaMensagem='Erro status '+response.status;
		jQuery("#alert").modal('show');
	}
}

celsCompareApp.service('celscompareService',['$http',function($http){
	
	this.alertMessage = function($scope,message){
		$scope.alertaMensagem=message;
		jQuery("#alert").modal('show');
		
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
		        	
		    		
		        	callBack(response.data);
		         },function(response){
		        	 
		        	 error(response);
		         });
		
	}
	this.addFavorito = function(celular,callBack,error){
		
		$http.post("/addFavorito/"+celular,{})
		        .then(function (response) {
		        	
		        	callBack(response.data);
		         },function(response){
		        	
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
		    if($scope.celularesComparacao == null || celularCount($scope.celularesComparacao) == 0){
		    	$scope.celularesComparacao = [];
		    	jQuery("#compare").prop("disabled",true);
		    }
		    
		    if(celularCount($scope.celularesComparacao) > 1 ){
		    	return;
		    }
		    
		    if(celularCount($scope.celularesComparacao) == 1){
		    	var indexNull = 0;
		    	for	(index = 0; index < $scope.celularesComparacao.length; index++) {
		    	    celular = $scope.celularesComparacao[index];
		    	    if(celular != null){
		    	    	if($scope.celularesComparacao[index].idCelular == compareRow.idCelular){
							celscompareService.alertMessage($scope,"O celular já foi selecionado para comparação");
		                    return;
						}
		    	    } else {
						indexNull = index;
					}
		    	}
		    	if($scope.celularesComparacao.length == 2){
		    		$scope.celularesComparacao[indexNull] = compareRow;
		    	} else {
		    		$scope.celularesComparacao.push(compareRow);
		    	}
		    } else {
		    	$scope.celularesComparacao.push(compareRow);
		    }
		    
		   
			if(celularCount($scope.celularesComparacao) == 2){
				jQuery("#compare").prop("disabled",false);
			}
	  }
	  $scope.favorito = function(idCelular){
		  celscompareService.addFavorito(idCelular,function(data){
			  jQuery("#formFavorito#"+ idCelular).submit();
		  },errorCallback($scope))
	  }
	  $scope.compare = function(){
		   celscompareService.buscaComparativos($scope.celularesComparacao[0].idCelular,
				  $scope.celularesComparacao[1].idCelular, function(response){
			  $scope.comparativo =   response;
			  setTimeout(function () {
			        $scope.$apply(function () {
			        	 formatMelhor();
			        	 jQuery("#comparativo").modal('show');
			        });
			   }, 100);
			  // $scope.$apply(function () {
				//  formatMelhor();
		     // });
			 
			  
		  },errorCallback($scope))
	  }
	  $scope.fecharComparativo = function(){
		  $scope.celularesComparacao = [];
		  jQuery("#compare").prop("disabled",true);
		  jQuery("#comparativo").modal('hide');
	  }
	  $scope.limpar = function(numerocelular){
		  var celulares  = $scope.celularesComparacao;
		  if(numerocelular == 2){
			  $scope.celularesComparacao = [];
			  $scope.celularesComparacao.push(celulares[0]);
		  } else {
			  $scope.celularesComparacao[0]=null;
		  }
		  jQuery("#compare").prop("disabled",true);
	  }
	  
	  
	}]);
celsCompareApp.controller('favoritoList',['$scope', 'celscompareService', function ($scope,celscompareService) {
	  celscompareService.findAllOrderByFavoritos(function(response){
		 $scope.favoritos =  response;  
		   
	  });
	 
	
	}]);

