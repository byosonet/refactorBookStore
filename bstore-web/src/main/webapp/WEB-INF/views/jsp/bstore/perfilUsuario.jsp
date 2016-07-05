<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<c:set var="fechaNacimiento">
     <fmt:formatDate pattern="ddMMyyyy" value="${usuario.fechaNacimiento}" />
</c:set>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="text/javascript" language="javascript" src="${contextpath}/static/resources/js/model/ModelNacimiento.js"></script>
  
  <style type="text/css">
		html * {font-family: Verdana;font-size: 12;}
  </style>
  
  <script type="text/javascript">
      $(function(){
    	$('select#dia option[value=${fechaNacimiento.substring(0,2)}]').attr('selected','selected'); 
    	$('select#dia').select2();
        
    	$('select#anio option[value=${fechaNacimiento.substring(4,8)}]').attr('selected','selected'); 
        $('select#anio').select2();
        
        $('select#mes option[value=${fechaNacimiento.substring(2,4)}]').attr('selected','selected'); 
        $('select#mes').select2();
        
        $('select#actividad option[value=${usuario.actividad}]').attr('selected','selected');
        $('select#actividad').select2();
          
        var status;
        $('input#masculino').click(function(){
            $('input#femenino').attr('checked',false);
        });

        $('input#femenino').click(function(){
            $('input#masculino').attr('checked',false);
        });
          
          
        $('input#nombre').focus();
        $('button#registrar').click(function(){
            
                var nombre = $('input#nombre');
                var email = $('input#email');
                var passw = $('input#password');
                var confPassword = $('input#confPassword');
                if(nombre.val() === ""){
                    nombre.focus();
                    muestraMsjSistemaError('El nombre es requerido.');
                    return false;
                }else if(email.val() === ""){
                    muestraMsjSistemaError('El email es requerido.');
                    return false;
                }else if(passw.val() === "" || confPassword.val() === ""){
                    muestraMsjSistemaError('El password es requerido.');
                    return false;
                }else if(passw.val() != confPassword.val()){
                    muestraMsjSistemaError('El password no coincide.');
                    return false; 
                }
                var m = $('input#masculino').filter(":checked").val();
                var f = $('input#femenino').filter(":checked").val();
		if(m === undefined && f === undefined){
			  muestraMsjSistemaError('El sexo es un campo requerido.');
			  return false;
		}
                $('input#idEmail').val(email.val());
                $('input#idPassword').val(passw.val());
		$.blockUI();
                $.ajax({
	              type: 'POST',
	              url: '${contextpath}'+'/perfil/actualizar',
	              data: $('form#formRegistrar').serialize(),
	                  success: function (data) {
                             $.unblockUI();
                             muestraMsjSistemaSuccess(data.mensaje);
	              },
                         error: function(msj){
                             status = JSON.parse(msj.responseText);
                             $.unblockUI();
                             muestraMsjSistemaError(status.mensaje);
                          }
	        });
        });
        
        function muestraMsjSistemaError(msjStatus){
           BootstrapDialog.show({
            size: BootstrapDialog.SIZE_SMALL,
            title: 'Mensaje del Sistema:',
            closable: false,
            message: msjStatus,
            type: BootstrapDialog.TYPE_DANGER,
            cssClass: 'login-dialog',
            buttons: [{
                icon: 'glyphicon glyphicon-check',
                label: 'OK',
                cssClass: 'btn-primary',
                action: function(dialog) {
                    dialog.close();
                }
            }]
        });
        }
        
        function muestraMsjSistemaSuccess(msjStatus){
           BootstrapDialog.show({
            size: BootstrapDialog.SIZE_SMALL,
            title: 'Mensaje del Sistema:',
            closable: false,
            message: msjStatus,
            type: BootstrapDialog.TYPE_SUCCESS,
            cssClass: 'login-dialog',
            buttons: [{
                icon: 'glyphicon glyphicon-check',
                label: 'CONTINUAR',
                cssClass: 'btn-primary',
                action: function(dialog) {
                    dialog.close();
                    $.blockUI();
                    var urlAction = '${contextpath}' + '/equivira';
                    document.getElementById('ingresar').action = urlAction;
                    document.getElementById('ingresar').method = 'POST';
                    document.getElementById('ingresar').submit();
                }
            }]
        });
        }
        
        
        
      });
  </script>
    </head>
    <body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12">
            <form class="form-horizontal" id="formRegistrar" method="post" action="${contextpath}/actualizar">
            
            	<div class="form-group">
                    <div class="control-label col-sm-12 alert alert-info" style="text-align: center;">Informaci&oacute;n de Usuario</div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-2"  for="nombre">Nombre:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa tu nombre" value="${usuario.nombre}">
                    </div>
                    
                    <label class="control-label col-sm-2"  for="apaterno">Apellido Paterno:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="apaterno" name="apaterno" placeholder="Ingresa tu apellido paterno" value="${usuario.APaterno}">
                    </div>
                    
                </div>
                
                 <div class="form-group">
                    <label class="control-label col-sm-2"  for="amaterno">Apellido Materno:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="amaterno" name="amaterno" placeholder="Ingresa tu apellido materno" value="${usuario.AMaterno}">
                    </div>
                    
                    <label class="control-label col-sm-1"  for="email">Email:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Ingesa tu email" value="${usuario.email}">
                    </div>
                </div>

                <div class="form-group">
                   <label class="control-label col-sm-2"  for="pass1">Password:</label>
                    <div class="col-sm-3">
                        <input type="password" class="form-control" id="pass1" name="pass1" placeholder="Ingesa tu password" value="${usuario.password}">
                    </div>
                   
                   <label class="control-label col-sm-3"  for="pass2">Confirmar password:</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="pass2" name="pass2" placeholder="Confirma tu password" value="${usuario.password}">
                    </div>
                </div>
                
                
                </br>
                </br>
                <div class="form-group">
                    <div class="control-label col-sm-12 alert alert-info" style="text-align: center;">Datos personales (Fecha Nacimiento)</div>
                </div>
                
                <div class="form-group">
                    <label class="control-label col-sm-2" >Día:</label>
                    <div class="col-sm-1">
                        <select class="form-control" id="dia" name="dia" data-bind="foreach: days, visible: days().length > 0">
                        <option data-bind="value: id,text:day"></option></select>
                    </div>
                    
                    <label class="control-label col-sm-1" >Mes:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="mes" name="mes" data-bind="foreach: months, visible: months().length > 0">
                        <option data-bind="value: id,text:mes"></option></select>
                    </div>
                    
                    <label class="control-label col-sm-1" >Año:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="anio" name="anio" data-bind="foreach: years, visible: years().length > 0">
                        <option data-bind="value: year,text:year"></option></select>
                    </div>
                   
                    
                    <label class="control-label col-sm-1" >Actividad:</label>
                    <div class="col-sm-2">
                        <select class="form-control" id="actividad" name="actividad" data-bind="foreach: activities, visible: activities().length > 0">
                        <option data-bind="value: activity,text:activity"></option></select>
                    </div>
                </div>
                
                
                 <div class="form-group">
                    <label class="control-label col-sm-2" >Sexo:</label>
                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                            <c:if test="${usuario.sexo == 'M'}">
                            	<input type="radio" id="masculino" name="sexo" value="M" checked>
                            </c:if>
                            <c:if test="${usuario.sexo == 'F'}">
                            	<input type="radio" id="masculino" name="sexo" value="M">
                            </c:if>
                            
                            <label > Masculino </label>
                        </div>
                    </div>

                    <div class="col-sm-2">
                       <div class="radio radio-info radio-inline">
                       		<c:if test="${usuario.sexo == 'F'}">
                       			<input type="radio" id="femenino" name="sexo" value="F" checked>
                       		</c:if>
                       		<c:if test="${usuario.sexo == 'M'}">
                       			<input type="radio" id="femenino" name="sexo" value="F">
                       		</c:if>
                            <label > Femenino </label>
                        </div>
                    </div>
     
                    <label class="control-label col-sm-3" >Deseo recibir notificaciones:</label>
                    <div class="col-sm-3">
                       <div class="checkbox checkbox-primary">
                       <c:if test="${usuario.notificaciones == 'SI'}">
                       		<input type="checkbox" value="SI" name="notificar" id="notificar" checked>
                       </c:if>
                       <c:if test="${usuario.notificaciones == 'NO'}">
                       		<input type="checkbox" value="SI" name="notificar" id="notificar">
                       </c:if>
                        <label>
                            Sí
                        </label>
                    </div>
                    </div>
                </div>
            </form>
                
            <form id="ingresar">
                <input type="hidden" id="idEmail" name="user" value=""/>
                <input type="hidden" id="idPassword" name="password" value=""/>
            </form>
            <div class="row">
                <div class="col-sm-offset-2 col-sm-10" style="text-align: right;">
                <button id="registrar" class="btn btn-primary" disabled><span class="glyphicon glyphicon-user"></span> ACTUALIZAR</button>
                </div>
            </div>
        </div>
    </div>    
</div>
<br>
<br>
<br>
</body>
</html>
