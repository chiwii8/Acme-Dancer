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
			<th><spring:message code="style.name"/></th>
			<th><spring:message code="style.description"/></th>
			<th><spring:message code="style.action"/></th>
			
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${styles}" var="style">
			<tr>
				<td><a href="style/view.do?styleId=${style.id}">${style.nombre}</a></td>
				<td>${style.descripcion}</td>
				<security:authorize access="hasRole('ADMIN')">
					<td>
						<a href="administrator/style/edit.do?styleId=${style.id}"> <spring:message
						code="style.edit" />
						</a>
					</td>
		
				</security:authorize>
				
			</tr>
		</jstl:forEach>
	</tbody>
</table>

<!-- Action links -->


<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="administrator/style/create.do"> <spring:message
				code="style.create" />
		</a>
	</div>
</security:authorize>