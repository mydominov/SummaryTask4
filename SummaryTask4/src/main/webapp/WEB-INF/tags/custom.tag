<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="userRole" type="java.lang.Short"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="us" uri="/WEB-INF/custom.tld"%>

	<c:if test="${userRole eq 100}">
		<li style="margin-top: 15px;">
			<us:form-post link="/SummaryTask4/pages/admin/commandMenu" text="${lang.section2}"/>
		</li>
	</c:if>
	<c:if test="${userRole eq 90}">
		<li style="margin-top: 15px;">
			<us:form-post link="/SummaryTask4/pages/manager/order-list" text="${lang.section2}"/>
		</li>
	</c:if>
	<c:if test="${(userRole le 50) && (userRole gt 0)}">
		<li style="margin-top: 15px;">
			<us:form-post link="/SummaryTask4/pages/client/tour-list" text="${lang.section2}"/>
		</li>
	</c:if>