<%@ include file="../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<c:set var="urlhref"><spring:message code="url.menu.href"/></c:set>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><tiles:insertAttribute name="title"/></title>
    <jsp:include page="staticResources.jsp"></jsp:include>
    
    	<script type="text/javascript">
            $(document).ready(function() {                               
                    $('a#exit').click(function(){
                        $.ajax({
                            type: 'POST',
                            url:  '${contextpath}'+'/sistema/salir',
                            data: $('form#regresar').serialize(),
                             success: function (data) {
                                   muestraMsjSistemaSuccessIndex("Gracias por tu visita, vuelve pronto.");
                            }  
                          });
                    });          
                              
                    function muestraMsjSistemaSuccessIndex(msjStatus){
                       BootstrapDialog.show({
                        size: BootstrapDialog.SIZE_SMALL,
                        title: 'Mensaje del Sistema',
                        closable: false,
                        message: msjStatus,
                        type: BootstrapDialog.TYPE_SUCCESS,
                        cssClass: 'login-dialog',
                        buttons: [{
                            icon: 'glyphicon glyphicon-ok',
                            label: 'ACEPTAR',
                            cssClass: 'btn-primary',
                            action: function(dialog) {
                                dialog.close();
                               $.blockUI();
                               var urlAction = '${urlhref}';
                               document.getElementById('index').action = urlAction;
                               document.getElementById('index').method = 'GET';
                               document.getElementById('index').submit();
                            }
                        }]
                    });
                    }   
                }          
            );
        </script>
</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a id="exit" class="navbar-brand" href="#">eQuivira</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
            	<c:if test="${not empty ultimasCompras}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> Compras recientes <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                    	<c:forEach var="publicacion" items="${ultimasCompras}">
	                        <li class="message-preview">
	                            <a href="${contextpath}/publicacion/${publicacion.id}">
	                                <div class="media">
	                                    <span class="pull-left">
	                                        <img alt="" src="http://localhost:8080/bstore/static/resources/img/user.png" class="media-object" style="width: 20px;height: 20px">
	                                    </span>
	                                    <div class="media-body">
	                                        <h5 class="media-heading"><strong><c:out value="${sessionScope.usuario.email}"/></strong>
	                                        </h5>
	                                        <p class="small text-muted"><i class="fa fa-fw fa-calendar"></i> Compra <fmt:formatDate value="${publicacion.fechaCompraTemporal}" pattern="dd-MM-yyyy HH:mm:ss"/></p>
	                                        <p class="small text-muted"><i class="fa fa-fw fa-check"></i> ISBN <c:out value="${publicacion.isbn}"/></p>
	                                        <p class="small text-muted"><i class="fa fa-fw fa-book"></i> <b><c:out value="${publicacion.nombre}"/></b></p>
	                                    </div>
	                                </div>
	                            </a>
	                        </li>
                        </c:forEach>
                    </ul>
                </li>
                </c:if>
                <li class="dropdown">
                	<c:if test="${sessionScope.usuario.perfil.nombre == 'ADMIN'}">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-key"></i> Nivel: Administrador </a>
                    </c:if>
                    <c:if test="${sessionScope.usuario.perfil.nombre == 'LECTOR'}">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-key"></i> Nivel: Lector </a>
                    </c:if>
                    
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Hola <c:out value="${sessionScope.usuario.nombre}"/> <c:out value="${sessionScope.usuario.APaterno}"/> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Mi perfil</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Configuraci&oacute;n</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#" id="exit"><i class="fa fa-fw fa-power-off"></i> Salir</a>
                        </li>
                    </ul>
                </li>
                
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                	<li class="active">
                        <a href="${contextpath}/equivira"><i class="fa fa-fw fa-home"></i> Principal</a>
                    </li>
                    <li class="active">
                        <a href="${contextpath}/colecciones"><i class="fa fa-fw fa-star"></i> Colecciones</a>
                    </li>
                    <c:forEach var="entry" items="${menu}">
	                    <li class="active">
	                        <a href="#" data-toggle="collapse" data-target="#${entry.key.id}"><i class="fa fa-fw fa-folder-open"></i> <c:out value="${empty entry.key.nombreMostrar?entry.key.nombre:entry.key.nombreMostrar}"/> </a>
	                        <ul id="${entry.key.id}" class="collapse">
							<c:forEach var="publicacion" items="${entry.value}">
						    	<li>
	                                <a style="color: white;" href="${contextpath}/publicacion/${publicacion.id}"><i class="fa fa-fw fa-book"></i> <c:out value="${publicacion.nombre}"/></a>
	                            </li>
							</c:forEach>
							</ul>
	                    </li>
					</c:forEach>
					
					<!-- Menu del Admin -->
					<c:if test="${sessionScope.usuario.perfil.nombre == 'ADMIN'}">
						<li class="active">
	                        <a href="#" data-toggle="collapse" data-target="#admin"><i class="fa fa-fw fa-user"></i> Administrador</a>
	                        <ul id="admin" class="collapse">
						    	<li>
	                                <a style="color: white;" href="${contextpath}/editorial/getAll"><i class="fa fa-fw fa-plus-square"></i> Secci&oacute;n Editoriales</a>
	                            </li>
							</ul>
	                    </li>
                    </c:if>
                    
                </ul>
            </div>
        </nav>
        <div id="page-wrapper">
            <div class="container-fluid">
                <!--<div class="row">
                    <div class="col-lg-12" style="text-align: center;">
                        <h2 class="alert alert-info page-header">
                           <small><tiles:insertAttribute name="header" /></small>
                        </h2>
                    </div>
                </div>-->
                <br>
				<div id="contenido">
            		<tiles:insertAttribute name="contenido" />
        		</div>
            </div>
        </div>
    </div>
    
<form id="regresar">
   <input type="hidden" id="cifrar" name="cifrar" value="${cifrar}">
</form>
<form id="index">
</form>
</body>
</html>
