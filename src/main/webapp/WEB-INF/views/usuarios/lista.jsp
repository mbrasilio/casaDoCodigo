<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Lista de Pedidos">

    <div class="container" style="margin-bottom: 40px;">
    	<h2><a href="${s:mvcUrl('UC#form').build() }" rel="nofollow"><fmt:message key="lista.usuarios.novoUsuario"/></a></h2>
        <h1><fmt:message key="lista.usuarios"/></h1>
        <p style="color: red;">${erro}</p>
        <p style="color: green;">${sucesso}</p>
        <table class="table table-bordered table-striped table-hover">
            <tr>
                <th><fmt:message key="lista.usuarios.nome"/></th>
                <th>Email</th>
                <th>Role</th>
				<th></th>
            </tr>
           
           <c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome}</td>
					<td>${usuario.email}</td>
					<td>${usuario.roles}</td>
					<td>
						<form:form action="${s:mvcUrl('UC#mostrarRoles').arg(0,usuario.email).arg(1,usuario.nome).build() }"> 
							<input type="image"
								src="${contextPath }resources/imagens/plus2.png" name="plus"
								alt="Plus" title="Plus" />
						</form:form>
					</td>
				</tr>
			</c:forEach>
        </table>
    </div>

</tags:pageTemplate>