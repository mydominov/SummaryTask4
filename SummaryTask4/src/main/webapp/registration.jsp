<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="WEB-INF/custom.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
<link rel="shortcut icon" href="<c:url value="/resources/pic/icon.png"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/view.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu-bar.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/signup.css"/>">
<script src='https://www.google.com/recaptcha/api.js'></script>
<script src="resources/script/validatereg.js"></script>
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
						<li class="active"><a href="<c:url value="/registration.jsp"/>"><span
								class="glyphicon glyphicon-user"></span> ${lang.signup}</a></li>
						<li><a href="<c:url value="/login.jsp"/>"><span
								class="glyphicon glyphicon-log-in"></span> ${lang.login}</a></li>
					</c:if>
					<c:if test="${not empty user}">
						<li><a href="<c:url value="/pages/settings"/>">${user.name}</a></li>
						<li><a href="<c:url value="/pages/logout"/>"> <button type="submit" class="btn btn-default btn-xs">${lang.logout}</button></a></li>
					</c:if>
					<li><a>
							<form action="<c:url value="/registration.jsp"/>" method="POST">
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
		<h3 id="header">${lang.signup}</h3>
		<c:if test="${not empty regEx}">
			<div class="alert alert-danger">
  				<strong>Error!</strong> ${regEx}
			</div>
        </c:if>  
		<form name="reg-form" action="<c:url value="/pages/signup"/>" onsubmit="return validateForm()" method="POST" accept-charset="UTF-8">
		<label class="control-label" for="name">${lang.name}:</label>
		<div class="controls">
			<input name="name" class="form-control" type="text" placeholder="${lang.writename}" pattern="[A-ZА-ЯА-Я][a-zа-яа-я]{2,15}" title="Invalid name! The name should be more than 2 characters and begin with a capital letter">
		</div>
		<p>${lang.email}: <input name="email" class="form-control" type="email" placeholder="example123@ex.com" pattern="[A-Za-z][A-Za-z0-9_-.]*@[a-z]{2,10}\.[a-z]{2,3}" title="Invalid email! The main part consists of letters and decimals, the part after '@' consists of domen name." required></p>
		<p>${lang.password}: <input name="pass" class="form-control" type="password" placeholder="${lang.writepassword}" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})" title="Invalid password! The password must contain uppercase letters, lowercase letters and numbers and length must be more then 6 cells!" required></p>
		<p>${lang.repassword}:<input name="repass" class="form-control" type="password" placeholder="${lang.rewritepassword}" data-validation-match-match="pass"></p>
		<div class="g-recaptcha" data-sitekey="6Ldt6wkTAAAAAOF1Ht-n0uzyUMzD_y1NlCdDcP-K"></div>
		<br>
		<button type="submit" class="btn btn-primary">${lang.submit}</button>
		</form>

	</div>
</body>
</html>