<%@ include file="../../layout/taglibs.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contextpath" value="<%=request.getContextPath()%>" />
<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#coleccion").gridalicious({
                    gutter: 25,
                    width: 350,
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
                    <p class="text" style="font-size: 14"><c:out value="${coleccion.descripcion}"/></p>
                    <b><a href="${contextpath}/coleccion/${coleccion.id}" class="text btn btn-primary" style="font-size:15;width:100%;padding:5px;text-align:center;margin-bottom:-10px;margin-top:-20px;">
                            <i class="fa fa-folder-open-o"></i> <c:out value="${empty coleccion.nombreMostrar?coleccion.nombre:coleccion.nombreMostrar}"/>
                        </a></b>

                </div> 
            </c:forEach>         
        </div>
    </body>
</html>