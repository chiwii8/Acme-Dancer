<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:choose>
	<jstl:when test="${not empty courses}">
		<table>
	<thead>
		<tr>
			<th><spring:message code="request.course.name" /></th>
			<th><spring:message code="request.course.style" /></th>
			<th><spring:message code="request.course.startdate" /></th>
			<th><spring:message code="request.course.enddate" /></th>
			<th><spring:message code="request.course.dayofweek" /></th>
			<th><spring:message code="request.course.time" /></th>
			<th><spring:message code="request.course.level" /></th>
			<th><spring:message code="request.action" /></th>
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${courses}" var="course">
			<tr>
				<td>${course.titulo}</td>
				<td>${course.estilo.nombre}</td>
				<td>${course.fechaInicio}</td>
				<td>${course.fechaFin}</td>
				<td>${course.diaSemana}</td>
				<td>${course.hora}:${course.minuto}</td>
				<td>${course.nivel}</td>
				<td>
					<form action="student/request/request.do" method="post">
						<input type="hidden" name="courseId" value="${course.id}" />
						<input type="submit" value="<spring:message code='request.action' />" />
					</form>
					
				</td>
			
				
			</tr>
		</jstl:forEach>
	</tbody>
</table>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="request.notfound"/>
	</jstl:otherwise>
</jstl:choose>