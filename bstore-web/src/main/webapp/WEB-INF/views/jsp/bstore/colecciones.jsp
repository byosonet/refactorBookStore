<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#coleccion").gridalicious({
                    gutter: 25,
                    width: 280,
                    animate: true
                });
            }
            );
        </script>
    </head>
    <body>
        <div id="coleccion">
            <c:forEach var="coleccion" items="${colecciones}">
                <div class="item">
                    <a href="${contextpath}/coleccion/${coleccion.id}"><img class="zoom" style="border-radius:10px;margin-top:-20px;width: 100%;" src="${coleccion.portadaUrl}"></a>
                    
                    <c:if test="${not empty coleccion.descripcion}">
                    	<textarea readonly="readonly" class="text" rows="7" cols="28" id="aboutDescription" style="white-space: pre-line;text-align: justify;resize: none;margin-top:-40px;border: none;">
                    		<c:out value="${coleccion.descripcion}"/>
                    	</textarea>
                    </c:if>
                    
                    <b><a href="${contextpath}/coleccion/${coleccion.id}" class="text btn btn-primary" style="width:100%;padding:5px;text-align:center;margin-top:-40px;">
                            <i class=""></i> <c:out value="${empty coleccion.nombreMostrar?coleccion.nombre:coleccion.nombreMostrar}"/>
                        </a></b>

                </div> 
            </c:forEach>         
        </div>
    </body>
</html>