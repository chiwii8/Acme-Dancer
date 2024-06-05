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


<table>
	<thead>
		<tr>
			<th><spring:message code="administrator.tutorial.tutorial" /></th>
			<th><spring:message code="administrator.tutorial.description" /></th>
			<th><spring:message code="administrator.tutorial.visualization" /></th>

		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${tutorials}" var="tutorial">
			<tr>
				<td>${tutorial.nombre}</td>
				<td>${tutorial.descripcion}</td>
				<td>${tutorial.visualizaciones}</td>

			</tr>
		</jstl:forEach>
	</tbody>
</table>