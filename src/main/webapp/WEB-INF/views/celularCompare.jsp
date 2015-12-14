<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="celsCompareApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Celular Comparação</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js">
	
</script>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous">
	
</script>
<script src="/js/celscompare.js" charset="UTF-8" type="text/javascript" >
</script>
<style type="text/css">
#compare{
	cursor: pointer;
	float: left;
	left: 25%;

}
#addCompare {
	cursor: pointer;
	float: left;
	left: 25%;
}
#limparCel{
cursor: pointer;
}
#comparativo_dialog{
 width: 1000px;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
	</link>
<link rel="stylesheet" href="/css/celscompare.css">
</link>
</head>
<body ng-controller="celularList">
	<div class="container">
		<div class="page-header">
			<h2>Celular Comparação</h2>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>Celular 1</th>
					<th></th>
					<th>Celular 2</th>
					<th></th>
					<th>Comparar</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="30%" style="vertical-align:middle">{{celularesComparacao[0].modelo}}</td>
					<td width="16%" style="vertical-align:middle">
					<button class="btn btn-default"
					         style="{{celularesComparacao[0].modelo == null ? 'visibility: hidden;':''}}"
							data-toggle="modal" ng-click="limpar(1)"
							id="limpar" type="button" >Limpar</button>
					</td>
					<td width="30%" style="vertical-align:middle">{{celularesComparacao[1].modelo}}</td>
					<td width="16%" style="vertical-align:middle">
					<button class="btn btn-default"
					         style="{{celularesComparacao[1].modelo == null ? 'visibility: hidden;':''}}"
							data-toggle="modal" ng-click="limpar(2)"
							id="limpar" type="button" >Limpar</button>
					</td>
						
					<td>
					<button class="btn btn-primary" disabled="disabled"       
							data-toggle="modal" ng-click="compare()"
							id="compare" type="button" >OK</button>
				   
							</td>
				</tr>
			</tbody>
		</table>
 
		<table  class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Celular</th>
					<th>Favorito</th>
				</tr>
			</thead>
			<tbody >
				<tr ng-repeat="phone in celulares">
					<td width="92%"><a href="#" ng-click="addCompare(phone)">{{phone.modelo}}</a></td>
					<td >
						<form id="{{'formFavorito#'+ phone.idCelular}}"
							action="listaFavoritos">
							<!--<button style="heigth:30px" class="btn btn-primary"        
							data-toggle="modal" ng-click="favorito(phone.idCelular)"
							id="addCompare" type="button" >OK</button>-->
							 <span id="addCompare" ng-click="favorito(phone.idCelular)"
								class="glyphicon glyphicon-ok-circle"></span> 

						</form>
					</td>

				</tr>
			</tbody>
		</table>


		<!-- 
		<div class="panel panel-default">
			<div class="panel-heading"><h3>Celulares</h3></div>
			<div class="panel-body">
				<p>Escolha dois celulares para efetuar a comparacao</p>
			</div>
			
		</div>
 -->



	</div>

	<!-- Modal -->
	<div class="modal fade" id="comparativo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div id="comparativo_dialog" class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" ng-click="fecharComparativo()"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Comparativo</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<thead>
							<tr>
							    <th>Requisito</th>
								<th>{{comparativo.descricaoCelular1}}</th>
								<th>{{comparativo.descricaoCelular2}}</th>
							</tr>
						</thead>
						<tbody>
							 
							<tr ng-repeat="requisito in comparativo.requisitos">
								<td width="40%">{{requisito.descricao}}</td>
								<td width="30%">{{requisito.valorCelular1}}{{requisito.celular1emelhor ? '  (Melhor)':''}}</td>
								<td width="30%">{{requisito.valorCelular2}}{{requisito.celular2emelhor ? '  (Melhor)':''}}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" ng-click="fecharComparativo()">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="alert" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div id="alert_dialog" class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Celular Comparação</h4>
				</div>
				<div class="modal-body">
					<h5>{{alertaMensagem}}</h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	


</body>
</html>