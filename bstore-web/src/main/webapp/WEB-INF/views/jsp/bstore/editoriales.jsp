<%@ include file="../../layout/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$('#tablaEditoriales').DataTable({
				"language" : {
					"lengthMenu" : "Mostrar _MENU_ registros por página",
					"zeroRecords" : "No hay registros",
					"info" : "Página _PAGE_ de _PAGES_",
					"infoEmpty" : "No hay registros",
					"infoFiltered" : "(Filtrado de _MAX_ registros)",
					"sSearch" : "Buscar:",
					"oPaginate" : {
						"sFirst" : "Primero",
						"sLast" : "Último",
						"sNext" : "Siguiente",
						"sPrevious" : "Anterior"
					},
				}
			});
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editoriales</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-sm-offset-0 col-md-12">
				<div class="form-group">
                    <div class="control-label col-sm-12 alert alert-info" style="text-align: center;">Editoriales</div>
                </div>
                <div class="form-group">
				  <br /> <br /> <br />
				</div>
				<div class="form-group">
					<a href="${contextpath}/editorial/add"
						class="btn btn-primary pull-right"
						style="font-size: 15; width: 15%; padding: 5px; text-align: center; margin-bottom: 20px; margin-top: -20px; align: right;">
						<i class="fa fa-plus"></i> <c:out value="Agregar editorial" />
					</a>
				</div>
				<div class="form-group">
				<table id="tablaEditoriales"
					class="table table-striped table-bordered" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th class="alert alert-success">Id</th>
							<th class="alert alert-success">Nombre</th>
							<th class="alert alert-success">Estatus</th>
							<th class="alert alert-success">RFC</th>
							<th class="alert alert-success">Telefono</th>
							<th class="alert alert-success">E-mail</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Estatus</th>
							<th>RFC</th>
							<th>Telefono</th>
							<th>E-mail</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="editorial" items="${editoriales}">
							<tr>
								<td>${editorial.id}</td>
								<td>${editorial.nombre}</td>
								<td>${editorial.estatus}</td>
								<td>${editorial.rfc}</td>
								<td>${editorial.telefono}</td>
								<td>${editorial.email}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>