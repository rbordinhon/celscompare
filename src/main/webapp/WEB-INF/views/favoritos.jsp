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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
			
			<h2  >Favoritos
			<form style="float:right" action="/celularCompare">
				<button   type="submit" class="btn btn-default" data-dismiss="modal">Voltar</button>
			</form></h2>
		</div>
			
		

		<table id="favoritos" class="table table-striped">
			<thead>
				<tr>
					<th>Rank</th>
					<th>Celular</th>
					<th>Usuários</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="phone in favoritos">
					<td width="10%">{{phone.rank}}</td>
					<td width="70%">{{phone.modelo}}</td>
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

	

</body>
</html>