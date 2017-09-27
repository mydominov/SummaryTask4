<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="/WEB-INF/custom.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Settings</title>
<link rel="shortcut icon" href="<c:url value="/resources/pic/icon.png"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/view.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu-bar.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/admin-menu.css"/>">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="resources/script/validatesettings.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>"><img id="logo"
					src="<c:url value="/resources/pic/logo.png"/>"></a>
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
						<li><a href="<c:url value="/login.jsp"/>"><span
								class="glyphicon glyphicon-log-in"></span> ${lang.login}</a></li>
					</c:if>
					<c:if test="${not empty user}">
						<li class="active"><a href="<c:url value="settings"/>">${user.name}</a></li>
						<li><a href="<c:url value="logout"/>"> <button type="submit" class="btn btn-default btn-xs">${lang.logout}</button></a></li>
					</c:if>
					<li><a>
							<form action="<c:url value="/pages/settings"/>" method="POST">
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
		<form action="<c:url value="/pages/client/watch-orders"/>" method="post">
			<button type="button" class="btn btn-primary btn-block" onclick="parentNode.submit();">${lang.monitororder}</button>
		</form>
		<form name="settings-form" action="<c:url value="/pages/client/edit-profile"/>" method="post" onsubmit="return validateForm()">
			<h3>${lang.name}: <input class="form-control" name="name" placeholder="${user.name}" type="text"></h3>
			<p>${lang.email}: <input class="form-control" name="email" placeholder="${user.email}" type="email"></p>
			<h4>${lang.chpassword}:</h4>
			<p>${lang.opassword}: <input class="form-control" name="old-password" type="password"></p>
			<p>${lang.npassword}: <input class="form-control" name="new-password" type="password"></p>
			<p>${lang.repassword}: <input class="form-control" name="repassword" type="password"></p>
			<button type="submit" class="btn btn-primary">${lang.submit}</button>
		</form>
	</div>
</body>
</html>