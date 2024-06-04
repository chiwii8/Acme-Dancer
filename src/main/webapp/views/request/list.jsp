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
	<jstl:when test="${not empty requests}">
		<table>
	<thead>
		<tr>
			<th><spring:message code="request.course.name"/></th>
			<th><spring:message code="request.date"/></th>
			<th><spring:message code="request.student.name"/></th>
			<th><spring:message code="request.state"/></th>
			<security:authorize access="hasRole('ACADEMIA')">
					<th>
						<spring:message code="course.request.action"/>
					</th>
		
			</security:authorize>
			
			
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${requests}" var="request">
			<tr>
				<td><a href="course/list.do?courseId=${request.curso.id}">${request.curso.titulo}</a></td>
				<td>${request.fecha}</td>
				<td>${request.alumno.nombre}</td>
				<td>${request.estado}</td>
				<security:authorize access="hasRole('ACADEMIA')">
					<td>
						<form action="academy/request/acceptrequest.do" method="POST">
							<input type="hidden" name="resquestId" value="${request.id}">
							<input type="submit" value='<spring:message code="request.action.accept"/>'/>
						</form>
						<form action="academy/request/rejectrequest.do" method="POST">
							<input type="hidden" name="resquestId" value="${request.id}">
							<input type="submit" value='<spring:message code="request.action.reject"/>'/>
						</form>
					
					</td>
		
				</security:authorize>
				
			</tr>
		</jstl:forEach>
	</tbody>
</table>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="request.notfound"/>
	</jstl:otherwise>
</jstl:choose>
