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
							<form action="<c:url value="/pages/admin/user-list"/>"
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
		<c:if test="${empty userList}">
			<h4>There is no users!</h4>
		</c:if>
		<c:if test="${not empty userList}">
			<h4>${lang.uslist}</h4>
			<div class="table">
				<div class="tr">
					<span class="td">ID</span>
					<span class="td">${lang.name}</span>
					<span class="td">${lang.email}</span>
					<span class="td">${lang.role}</span>
					<span class="td">${lang.purchasedtours}</span>
					<span class="td">${lang.balance}</span>
					<span class="td">Max tour name</span>
					<span class="td">Max price</span>
					<span class="td">${lang.update}</span>
					<span class="td">${lang.deleterec}</span>
				</div>
				<c:forEach var="userCl" items="${userList}">
					<form class="tr" action="<c:url value="/pages/admin/edit-user"/>" method="POST">
						<span class="td"><input name="id" class="form-control" value="${userCl.userId}"
									type="text" readonly></span>
						<span class="td">
							<input class="form-control" name="name" value="${userCl.name}" placeholder="${userCl.name}" type="text">
						</span>
						<span class="td">
							<input class="form-control" name="email" value="${userCl.email}" placeholder="${userCl.email}" type="email">
						</span>
						<span class="td">
							<select class="form-control" name="userrole">
								<c:if test="${not userCl.isConfirmed}">
									<c:forEach var="role" items="${userRoleList}">
										<c:if test="${role.roleName ne 'CLIENT'}">
											<c:if test="${userCl.userRole.roleName eq role.roleName}">
												<option selected>${userCl.userRole.roleName}</option>
											</c:if>
											<c:if test="${userCl.userRole.roleName ne role.roleName}">
												<option>${role.roleName}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${userCl.isConfirmed eq true}">
									<c:forEach var="role" items="${userRoleList}">
										<c:if test="${role.roleName ne 'UNCONFIRMED'}">
											<c:if test="${userCl.userRole.roleName eq role.roleName}">
												<option selected>${role.roleName}</option>
											</c:if>
											<c:if test="${userCl.userRole.roleName ne role.roleName}">
												<option>${role.roleName}</option>
											</c:if>
										</c:if>
									</c:forEach>
								</c:if>
							</select>
						</span>
						
						<span class="td"><span class="td"><input name="purchasedtours" class="form-control" value="${userCl.purchasedTours}"
									type="text" disabled></span></span>
						<span class="td">
							<input class="form-control" name="balance" value="${userCl.balance}" placeholder="${userCl.balance}" min="0">
						</span>
						<span class="td">${userCl.theMostExpensiveName}</span>
						<span class="td">${userCl.theMostExpensivePrice}</span>
						<span class="td"><button type="submit" class="btn btn-primary" name="submit" value="submit">${lang.submit}</button></span>
						<span class="td"><button type="button" class="btn btn-danger" name="submit" value="delete">${lang.delete}</button></span>
					</form>
				</c:forEach>
			</div>
		</c:if>
	</div>
</body>
</html>