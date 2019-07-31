<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Lista de Pedidos">

	<div class="container" style="margin-bottom: 40px;">
		<h1>
			<fmt:message key="usuarios.form" />
		</h1>
		<p style="color: red;">${erro}</p>
		<form:form action="${s:mvcUrl('UC#gravarUsuario').build() }" method="post"
			modelAttribute="usuario" enctype="multipart/form-data">
			
			<div class="form-group" style="margin-bottom: 5px;">
				<label><fmt:message key="usuarios.form.nome" /></label>
				<div>
					<form:input path="nome" cssClass="form-control"/>
					<form:errors path="nome" />
				</div>
			</div>

			<div class="form-group" style="margin-bottom: 5px;">
				<label><fmt:message key="usuarios.form.email" /></label>
				<div>
					<form:input path="email" cssClass="form-control" />
					<form:errors path="email" />
				</div>
			</div>

			<div class="form-group" style="margin-bottom: 5px;">
				<label><fmt:message key="usuarios.form.senha" /></label>
				<div>
					<form:password path="senha" id="password" cssClass="form-control" />
					<form:errors path="senha" />
				</div>
			</div>
			
			<div class="form-group" style="margin-bottom: 5px;">
				<label><fmt:message key="usuarios.form.senhaRepetida" /></label>
				<div>
					<form:password path="senhaRepetida" id="confirm_password" cssClass="form-control"/>
					<form:errors path="senhaRepetida" />
				</div>
			</div>
			
			<button id="botao_submit" class="btn btn-primary" type="submit"><fmt:message key="usuarios.form.cadastrar" /></button>
			
		</form:form>
	</div>

</tags:pageTemplate>

<script type="text/javascript">

	var password = document.getElementById("password"), confirm_password = document
			.getElementById("confirm_password");

	function validatePassword() {
		if (password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Senhas diferentes!");
		} else {
			confirm_password.setCustomValidity('');
		}
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
</script>





