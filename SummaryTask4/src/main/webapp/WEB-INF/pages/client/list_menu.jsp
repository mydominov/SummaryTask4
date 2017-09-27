<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="/WEB-INF/custom.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<c:url value="/resources/pic/icon.png"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/view.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/menu-bar.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/client.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/table.css"/>">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Main page</title>
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
						<li><a href="<c:url value="/pages/settings"/>">${user.name}</a></li>
						<li><a href="<c:url value="/pages/logout"/>">
								<button type="submit" class="btn btn-default btn-xs">${lang.logout}</button>
						</a></li>
					</c:if>
					<li><a>
							<form action="<c:url value="/pages/client/tour-list"/>" method="POST">
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
	<div class="container-fluid">
  <h1>${lang.section2}</h1>
  <div class="row">
    <form class="col-sm-3" style="background-color:lavender;" action="<c:url value="/pages/client/filter"/>" method="POST">
    	<ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="205">
			<li><h3>${lang.search}</h3></li>
    		<li>
    			<p>${lang.price}</p>
				<label for="min">${lang.min}</label>
				<input name="min" class="form-control" id="min" type="number" min="1">
  				<label for="max">${lang.max}</label>
  				<input name="max" class="form-control" id="max" type="number" min="1">
			</li>
			<li>
				<label for="holt">${lang.typeofholiday}:</label>
				<select id="holt" name="holidaytype" class="form-control">
					<option value="Nothing" selected>${lang.nothing}</option>
					<c:forEach var="holType" items="${holidayType}">
						<option>${holType.holidayName}</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label for="num">${lang.numberofpeople}:</label>
				<input name="numberofpeople" class="form-control" id="num" type="number" min="0" max="500">
			</li>
			<li>
				<label for="holt">${lang.hoteltype}:</label>
				<select name="hoteltype" id="holt" class="form-control">
					<option value="Nothing" selected>${lang.nothing}</option>
					<c:forEach var="hotType" items="${hotelType}">
						<option>${hotType.typeName}</option>
					</c:forEach>
				</select>
			</li>
			<li><br></li>
			<li><button class="btn btn-info" type="submit">${lang.submit}</button></li>
			<li><br></li>
		</ul>
    </form>
    <div class="col-sm-9" style="background-color:Azure;">
      <div class="table">
      	<div class="tr">
      		<span class="td">${lang.name}</span>
      		<span class="td">${lang.price}</span>
      		<span class="td">${lang.typeofholiday}</span>
      		<span class="td">${lang.numberofpeople}</span>
			<span class="td">${lang.hoteltype}</span>
			<span class="td">${lang.ishot}</span>
			<span class="td">${lang.discount}</span>
			<c:if test="${user.userRole.officeCoefficient ge 50}">
				<span class="td">${lang.submit}</span>
			</c:if>
      	</div>
      	<c:forEach var="tour" items="${freetours}">
      		<form class="tr" action="<c:url value="/pages/client/reserve-tour"/>" method="POST">
      			<span class="td"><input name="name" value="${tour.tourName}" type="text" class="form-control" readonly></span>
      			<span class="td">${tour.price}</span>
      			<span class="td">${tour.typeOfHoliday.holidayName}</span>
      			<span class="td">${tour.numberOfPeople}</span>
      			<span class="td">${tour.hotelType.typeName}</span>
      			<span class="td">
      				<c:if test="${tour.isHot eq true}">
      					<img alt="is hot" src="<c:url value="/resources/pic/fire.png"/>">
      				</c:if>
      			</span>
      			<span class="td">
      				<c:if test="${tour.stepDiscount gt 0}">
      					${(user.purchasedTours * tour.incrementDiscount) / tour.stepDiscount}
      				</c:if>
      				<c:if test="${tour.stepDiscount eq 0}">
      					no discount
      				</c:if>
      			
      			</span>
      			<c:if test="${user.userRole.officeCoefficient ge 50}">
      				<span class="td">
      					<button class="btn btn-info" type="submit">${lang.submit}</button>
      				</span>
      			</c:if>
      		</form>
      	</c:forEach>
      </div>
    </div>
  </div>
</div>
</body>
</html>