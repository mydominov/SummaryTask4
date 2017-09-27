<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="/WEB-INF/custom.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon"
	href="<c:url value="/resources/pic/icon.png"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/menu-bar.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/view.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/admin-menu.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/table.css"/>">
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
							<form action="<c:url value="/pages/manager/order-list"/>"
								method="POST">
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
	<div class="view">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#reserved">${lang.reservedtours}</a></li>
			<li><a data-toggle="tab" href="#tours">${lang.tours}</a></li>
		</ul>
		<br>
		<div class="tab-content">
			<div id="reserved" class="tab-pane fade in active">
				<c:if test="${empty reserved}">
					<h4>${lang.noreservedtours}</h4>
				</c:if>
				<c:if test="${not empty reserved}">
					<div class="table">
						<p class="caption">${lang.tours}</p>
						<div class="tr">
							<span class="td">ID</span> <span class="td">${lang.name}</span> <span
								class="td">${lang.price}</span> <span class="td">${lang.typeofholiday}</span>
							<span class="td">${lang.numberofpeople}</span> <span class="td">${lang.hoteltype}</span>
							<span class="td">${lang.tourstatus}</span> <span class="td">Reserved
								by</span> <span class="td">Max discount</span> <span class="td">Step
								discount</span> <span class="td">Increment discount</span> <span
								class="td">${lang.update}</span>
						</div>
						<c:forEach var="tour" items="${reserved}">
							<form class="tr"
								action="<c:url value="/pages/manager/serve-tour"/>"
								method="POST">
								<span class="td"> <input name="id" class="form-control"
									value="${tour.tourId}" type="text" readonly>
								</span> <span class="td"> <input name="tourname"
									class="form-control" value="${tour.tourName}"
									placeholder="${tour.tourName}" type="text" readonly>
								</span> <span class="td"> <input name="price"
									class="form-control" value="${tour.price}"
									placeholder="${tour.price}" type="number" readonly>
								</span> <span class="td"> <input name="holidtype"
									class="form-control" value="${tour.typeOfHoliday.holidayName}"
									placeholder="${tour.typeOfHoliday.holidayName}" type="text"
									disabled>
								</span> <span class="td"> <input name="numofperop"
									class="form-control" value="${tour.numberOfPeople}"
									placeholder="${tour.numberOfPeople}" type="number" disabled>
								</span> <span class="td"> <input name="hottype"
									class="form-control" value="${tour.hotelType.typeName}"
									placeholder="${tour.hotelType.typeName}" type="text" disabled>
								</span> <span class="td"> <select name="status"
									class="form-control">
										<option value="registrated" selected>${tour.tourStatus.statusName}</option>
										<option value="paid">paid</option>
										<option value="canceled">canceled</option>
								</select>
								</span> <span class="td"> <c:if test="${empty tour.reservedBy}">
										<p>Free</p>
									</c:if> <c:if test="${not empty tour.reservedBy}">
										<input name="reservedBy" class="form-control"
											value="${tour.reservedBy.email}"
											placeholder="${tour.reservedBy.email}" type="text" readonly>
									</c:if>
								</span> <span class="td"> <input name="maxdiscount"
									class="form-control" value="${tour.maxDiscount}"
									placeholder="${tour.maxDiscount}" type="number" min="0"
									max="100">
								</span> <span class="td"> <input name="stepdiscount"
									class="form-control" value="${tour.stepDiscount}"
									placeholder="${tour.stepDiscount}" type="number" min="0"
									max="100">
								</span> <span class="td"> <input name="incrementdiscount"
									class="form-control" value="${tour.incrementDiscount}"
									placeholder="${tour.incrementDiscount}" type="number" min="0"
									max="100">
								</span> <span class="td"><button class="btn btn-info"
										type="submit" name="submit" value="submit">${lang.submit}</button></span>
							</form>
						</c:forEach>
					</div>
				</c:if>
			</div>
		
		<div id="tours" class="tab-pane fade">
			<div class="table">
				<div class="tr">
					<span class="td">${lang.name}</span>
					<span class="td">${lang.price}</span>
					<span class="td">${lang.ishot}</span>
					<span class="td">${lang.submit}</span>
				</div>
				<c:forEach var="tour" items="${tours}">
					<form class="tr" action="<c:url value="/pages/manager/set-hot"/>" method="POST">
						<span class="td"><input name="name" class="form-control" value="${tour.tourName}" readonly></span>
						<span class="td"><input name="price" class="form-control" type="number" value="${tour.price}" disabled></span>
						<span class="td">
							<c:if test="${tour.isHot eq true}">
								<input type="checkbox" name="ishot" checked>
							</c:if> 
							<c:if test="${not tour.isHot}">
								<input type="checkbox" name="ishot">
							</c:if>
						</span>
						<span class="td"><button type="submit" class="btn btn-default">${lang.update}</button></span>
					</form>
				</c:forEach>
			</div>
		</div>
		</div>
	</div>
</body>
</html>