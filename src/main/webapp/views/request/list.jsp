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
	<jstl:when test="${not empty request}">
		<table>
	<thead>
		<tr>
			<th><spring:message code="request.course.name"/></th>
			<th><spring:message code="request.date"/></th>
			<th><spring:message code="request.student.name"/></th>
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
				<td><a href="course/list.do?courseId=${request.curso.id}">${request.curso.nombre}</a></td>
				<td>${request.fecha}</td>
				<td>${request.alumno.nombre}</td>
				<security:authorize access="hasRole('ACADEMIA')">
					<td>
						<a href="request/acceptrequest.do?requestId=${request.id}">
                            <spring:message code="request.action.accept"/>
                        </a>
						<a href="request/rejectrequest.do?request.Id=${request.id}">
                            <spring:message code="request.action.reject"/>
                        </a>
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
