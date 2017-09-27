<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="/WEB-INF/custom.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/client.css"/>">
<link rel="shortcut icon"
	href="<c:url value="/resources/pic/icon.png"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/view.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/menu-bar.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/index.css"/>">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Orders</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>"><img
					id="logo" src="<c:url value="/resources/pic/logo.png"/>"></a>
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
						<li><a href="<c:url value="/pages/settings"/>">${user.name}</a></li>
						<li><a href="<c:url value="/pages/logout"/>">
								<button type="submit" class="btn btn-default btn-xs">${lang.logout}</button>
						</a></li>
					</c:if>
					<li><a>
							<form action="<c:url value="/index.jsp"/>" method="POST">
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
	<h4>Your balance is ${user.balance}</h4>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>${lang.name}</th>
				<th>${lang.price}</th>
				<th>${lang.typeofholiday}</th>
				<th>${lang.numberofpeople}</th>
				<th>${lang.hoteltype}</th>
				<th>${lang.tourstatus}</th>
				<th>${lang.ishot}</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tour" items="${tours}">
			<tr>
				<th>${tour.tourName}</th>
				<th>${tour.price}</th>
				<th>${tour.typeOfHoliday.holidayName}</th>
				<th>${tour.numberOfPeople}</th>
				<th>${tour.hotelType.typeName}</th>
				<th>${tour.tourStatus.statusName}</th>
				<th>
					<c:if test="${tour.isHot eq true}">
      					<img alt="is hot" src="<c:url value="/resources/pic/fire.png"/>">
      				</c:if>
				</th>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>