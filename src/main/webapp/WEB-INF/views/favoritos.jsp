<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="celsCompareApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Favoritos</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js">
	
</script>
<script src="/js/celscompare.js">
	
</script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
	</link>
<link rel="stylesheet" href="/css/celscompare.css">
</link>
</head>
<body ng-controller="favoritoList">
	<div class="container">
		<div class="page-header">
			
			<h1  >Favoritos
			<form style="float:right" action="/celularCompare">
				<button   type="submit" class="btn btn-default" data-dismiss="modal">Voltar</button>
			</form></h1>
		</div>
			
		

		<table class="table">
			<thead>
				<tr>
					<th>Modelo</th>
					<th>Usuarios</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="phone in favoritos">
					<td width="80%">{{phone.modelo}}</td>
					<td width="20%">{{phone.votacoes}}</td>
					

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
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Comparativo</h4>
				</div>
				<div class="modal-body">
					<table class="table">
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>