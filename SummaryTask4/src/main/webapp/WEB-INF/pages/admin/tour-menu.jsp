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
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/view.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/menu-bar.css"/>">
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
<title>Tour menu</title>
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
							<form action="<c:url value="/pages/admin/tour-menu"/>"
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
	<div id="view">
		<div class="accordion" id="accordion2">
  			<div class="accordion-group">
    			<div class="accordion-heading">
      				<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
        				<span class="glyphicon glyphicon-plus"></span> ${lang.addtour}
      				</a>
    			</div>
    			<div id="collapseOne" class="accordion-body collapse in">
      				<div class="accordion-inner">
        				<div class="table">
        					<div class="tr">
								<span class="td">${lang.name}</span>
								<span class="td">${lang.price}</span>
								<span class="td">${lang.typeofholiday}</span>
								<span class="td">${lang.numberofpeople}</span>
								<span class="td">${lang.hoteltype}</span>
								<span class="td">${lang.tourstatus}</span>
								<span class="td">${lang.ishot}</span>
								<span class="td">${lang.maxdiscount}</span>
								<span class="td">${lang.stepdiscount}</span>
								<span class="td">${lang.incrementdiscount}</span>
								<span class="td">${lang.update}</span>
							</div>
							<form class="tr" action="<c:url value="/pages/admin/add-tour"/>" method="POST">
								<span class="td">
									<input name="name" placeholder="Name" type="text" class="form-control">
								</span>
								<span class="td">
									<input name="price" placeholder="$" type="number" class="form-control" min="1">
								</span>
								<span class="td">
									<select name="holidaytype" class="form-control">
										<c:forEach var="holType" items="${holidayType}">
											<option>${holType.holidayName}</option>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<input name="numberofpeople" placeholder="0" type="number" min="1" class="form-control">
								</span>
								<span class="td">
									<select name="hoteltype" class="form-control">
										<c:forEach var="hotType" items="${hotelType}">
											<option>${hotType.typeName}</option>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<select name="status" class="form-control">
										<c:forEach var="status" items="${tourStatus}">
											<option>${status.statusName}</option>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<input type="checkbox" name="ishot" class="form-control"> 
								</span>
								<span class="td">
									<input name="maxdiscount" type="number" value="0" min="0" max="100" class="form-control">
								</span>
								<span class="td">
									<input name="stepdiscount" type="number" value="0" min="0" max="100" class="form-control">
								</span>
								<span class="td">
									<input name="incrementdiscount" type="number" value="0" min="0" max="100" class="form-control">
								</span>
								<span class="td"><button class="btn btn-info" type="submit" name="submit" value="submit">${lang.submit}</button></span>
							</form>
        				</div>
      				</div>
    			</div>
  			</div>
  		</div>
  		<br>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#free">${lang.freetours}</a></li>
			<li><a data-toggle="tab" href="#booked">${lang.bookedtours}</a></li>
		</ul>
		<br>
		<div class="tab-content">
			<div id="free" class="tab-pane fade in active">
				<c:if test="${empty freetours}">
					<p>There is no free tours!</p>
				</c:if>
				<c:if test="${not empty freetours}">
					<div class="table">
						<p class="caption">${lang.tours}</p>
						<div class="tr">
							<span class="td">ID</span>
							<span class="td">${lang.name}</span>
							<span class="td">${lang.price}</span>
							<span class="td">${lang.typeofholiday}</span>
							<span class="td">${lang.numberofpeople}</span>
							<span class="td">${lang.hoteltype}</span>
							<span class="td">${lang.tourstatus}</span>
							<span class="td">${lang.ishot}</span>
							<span class="td">${lang.maxdiscount}</span>
							<span class="td">${lang.stepdiscount}</span>
							<span class="td">${lang.incrementdiscount}</span>
							<span class="td">${lang.update}</span>
							<span class="td">${lang.deleterec}</span>
						</div>
						<c:forEach var="tour" items="${freetours}">
							<form class="tr" action="<c:url value="/pages/admin/edit-tour"/>" method="POST">
								<span class="td">
									<input name="id" class="form-control" value="${tour.tourId}"
										type="text" readonly>
								</span>
								<span class="td">
									<input name="tourname" value="${tour.tourName}" placeholder="${tour.tourName}" type="text">
								</span>
								<span class="td">
									<input name="price" value="${tour.price}" placeholder="${tour.price}" type="number" min="1">
								</span>
								<span class="td">
									<select name="typeofholiday" class="form-control">
										<c:forEach var="holType" items="${holidayType}">
											<c:if test="${holType.typeOfHolidayId eq tour.typeOfHoliday.typeOfHolidayId}">
												<option selected>${tour.typeOfHoliday.holidayName}</option>
											</c:if>
											<c:if test="${holType.typeOfHolidayId ne tour.typeOfHoliday.typeOfHolidayId}">
												<option>${holType.holidayName}</option>
											</c:if>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<input name="numberofpeople" type="number" min="1"
										max="800" value="${tour.numberOfPeople}" placeholder="${tour.numberOfPeople}">
								</span>
								<span class="td">
									<select name="hoteltype" class="form-control">
										<c:forEach var="hotType" items="${hotelType}">
											<c:if
												test="${hotType.hotelTypeId eq tour.hotelType.hotelTypeId}">
												<option selected>${tour.hotelType.typeName}</option>
											</c:if>
											<c:if
												test="${hotType.hotelTypeId ne tour.hotelType.hotelTypeId}">
												<option>${hotType.typeName}</option>
											</c:if>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<select name="status" class="form-control">
										<c:forEach var="status" items="${tourStatus}">
											<c:if test="${status.statusId eq tour.tourStatus.statusId}">
												<option selected>${tour.tourStatus.statusName}</option>
											</c:if>
											<c:if test="${status.statusId ne tour.tourStatus.statusId}">
												<option>${status.statusName}</option>
											</c:if>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<c:if test="${tour.isHot eq true}">
										<input type="checkbox" name="ishot" class="form-control" checked>
									</c:if> 
									<c:if test="${not tour.isHot}">
										<input type="checkbox" name="ishot" class="form-control">
									</c:if>
								</span>
								<span class="td">
									<input name="maxdiscount" value="${tour.maxDiscount}" placeholder="${tour.maxDiscount}" type="number" min="0" max="100">
								</span>
								<span class="td">
									<input name="stepdiscount" value="${tour.stepDiscount}" placeholder="${tour.stepDiscount}" type="number" min="0" max="100">
								</span>
								<span class="td">
									<input name="incrementdiscount" value="${tour.incrementDiscount}" placeholder="${tour.incrementDiscount}" type="number" min="0" max="100">
								</span>
								<span class="td"><button class="btn btn-info" type="submit" name="submit" value="submit">${lang.submit}</button></span>
								<span class="td"><button class="btn btn-danger" type="submit" name="submit" value="delete">${lang.delete}</button></span>
							</form>
						</c:forEach>
					</div>
					
				</c:if>
			</div>

			<div id="booked" class="tab-pane fade">
				<c:if test="${empty bookedtours}">
					<p>There is no booked tours!</p>
				</c:if>
				<c:if test="${not empty bookedtours}">
					<div class="table">
						<p class="caption">${lang.tours}</p>
						<div class="tr">
							<span class="td">ID</span>
							<span class="td">${lang.name}</span>
							<span class="td">${lang.price}</span>
							<span class="td">${lang.typeofholiday}</span>
							<span class="td">${lang.numberofpeople}</span>
							<span class="td">${lang.hoteltype}</span>
							<span class="td">${lang.tourstatus}</span>
							<span class="td">${lang.reservedby}</span>
							<span class="td">${lang.ishot}</span>
							<span class="td">${lang.maxdiscount}</span>
							<span class="td">${lang.stepdiscount}</span>
							<span class="td">${lang.incrementdiscount}</span>
							<span class="td">${lang.update}</span>
							<span class="td">${lang.deleterec}</span>
						</div>
						<c:forEach var="tour" items="${bookedtours}">
							<form class="tr" action="<c:url value="/pages/admin/edit-tour"/>" method="POST">
								<span class="td">
									<input name="id" class="form-control" value="${tour.tourId}"
										type="text" readonly>
								</span>
								<span class="td">
									<input name="tourname" value="${tour.tourName}" placeholder="${tour.tourName}" type="text">
								</span>
								<span class="td">
									<input name="price" value="${tour.price}" placeholder="${tour.price}" type="number" min="1">
								</span>
								<span class="td">
									<select name="typeofholiday" class="form-control">
										<c:forEach var="holType" items="${holidayType}">
											<c:if test="${holType.typeOfHolidayId eq tour.typeOfHoliday.typeOfHolidayId}">
												<option selected>${tour.typeOfHoliday.holidayName}</option>
											</c:if>
											<c:if test="${holType.typeOfHolidayId ne tour.typeOfHoliday.typeOfHolidayId}">
												<option>${holType.holidayName}</option>
											</c:if>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<input name="numberofpeople" type="number" min="1"
										max="800" value="${tour.numberOfPeople}" placeholder="${tour.numberOfPeople}">
								</span>
								<span class="td">
									<select name="hoteltype" class="form-control">
										<c:forEach var="hotType" items="${hotelType}">
											<c:if
												test="${hotType.hotelTypeId eq tour.hotelType.hotelTypeId}">
												<option selected>${tour.hotelType.typeName}</option>
											</c:if>
											<c:if
												test="${hotType.hotelTypeId ne tour.hotelType.hotelTypeId}">
												<option>${hotType.typeName}</option>
											</c:if>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<select name="status" class="form-control">
										<c:forEach var="status" items="${tourStatus}">
											<c:if test="${status.statusId eq tour.tourStatus.statusId}">
												<option selected>${tour.tourStatus.statusName}</option>
											</c:if>
											<c:if test="${status.statusId ne tour.tourStatus.statusId}">
												<option>${status.statusName}</option>
											</c:if>
										</c:forEach>
									</select>
								</span>
								<span class="td">
									<c:if test="${empty tour.reservedBy}">
										<p>Free</p>
									</c:if>
									<c:if test="${not empty tour.reservedBy}">
										<input name="reservedBy" class="form-control" value="${tour.reservedBy.email}" placeholder="${tour.reservedBy.email}" type="text" readonly>
									</c:if>
								</span>
								<span class="td">
									<c:if test="${tour.isHot eq true}">
										<input type="checkbox" name="ishot" checked>
									</c:if> 
									<c:if test="${not tour.isHot}">
										<input type="checkbox" name="ishot">
									</c:if>
								</span>
								<span class="td">
									<input name="maxdiscount" value="${tour.maxDiscount}" placeholder="${tour.maxDiscount}" type="number" min="0" max="100">
								</span>
								<span class="td">
									<input name="stepdiscount" value="${tour.stepDiscount}" placeholder="${tour.stepDiscount}" type="number" min="0" max="100">
								</span>
								<span class="td">
									<input name="incrementdiscount" value="${tour.incrementDiscount}" placeholder="${tour.incrementDiscount}" type="number" min="0" max="100">
								</span>
								<span class="td"><button class="btn btn-info" type="submit" name="submit" value="submit">${lang.submit}</button></span>
								<span class="td"><button class="btn btn-danger" type="submit" name="submit" value="delete">${lang.delete}</button></span>
							</form>
						</c:forEach>
					</div>
					
				</c:if>
			</div>
		</div>
			

	</div>
</body>
</html>