<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="WEB-INF/custom.tld"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="shortcut icon" href="<c:url value="/resources/pic/icon.png"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu-bar.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/login.css"/>">
<script src="resources/script/validatelogin.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>"><img id="logo" src="<c:url value="/resources/pic/logo.png"/>"></a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="/about-us.jsp"/>">${lang.section1}</a></li>
					<us:SwapLinkByRole userRole="${user.userRole.officeCoefficient}"></us:SwapLinkByRole>
					<li><a href="<c:url value="/contacts.jsp"/>">${lang.section4}</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty user}">
						<li><a href="<c:url value="/registration.jsp"/>"><span
								class="glyphicon glyphicon-user"></span> ${lang.signup}</a></li>
						<li class="active"><a href="<c:url value="/login.jsp"/>"><span
								class="glyphicon glyphicon-log-in"></span> ${lang.login}</a></li>
					</c:if>
					<c:if test="${not empty user}">
						<li><a href="<c:url value="/pages/settings"/>">${user.name}</a></li>
						<li><a href="<c:url value="/pages/logout"/>"> <button type="submit" class="btn btn-default btn-xs">${lang.logout}</button></a></li>
					</c:if>
					<li><a>
							<form action="<c:url value="/login.jsp"/>" method="POST">
								<select name="language" onchange="this.form.submit()">
									<option value="ru">${lang.language}</option>
									<option value="ru">Русский</option>
									<option value="ua">Українська</option>
									<option value="en">English</option>
								</select>
							</form>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END MENU -->
	<div id="view">
		<h3 id="header">${lang.login}</h3>
		<c:if test="${not empty ex}">
			<div class="alert alert-danger">
  				<strong>Error!</strong> ${ex}
			</div>
        </c:if>
		<form name="login-form" action="<c:url value="/pages/login"/>" method="POST">
		<p>${lang.email}: <input name="email" class="form-control" pattern="[A-Za-z][A-Za-z0-9_-.]*@[a-z]{2,10}\.[a-z]{2,3}" title="Invalid email! The main part consists of letters and decimals, the part after '@' consists of domen name." type="text" placeholder="example123@ex.com"></p>
		<p>${lang.password}: <input name="pass" class="form-control" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})" title="Invalid password! The password must contain uppercase letters, lowercase letters and numbers and length must be more then 6 cells!" type="password" placeholder="${lang.writepassword}"></p>
		<br>
		<button type="submit" class="btn btn-success">${lang.submit}</button>
		</form>
	</div>
</body>
</html>