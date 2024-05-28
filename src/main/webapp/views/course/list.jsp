<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<table>
	<thead>
		<tr>
			<th><spring:message code="course.title"/></th>
			<th><spring:message code="course.style"/></th>
			<th><spring:message code="course.startdate"/></th>
			<th><spring:message code="course.enddate"/></th>
			<th><spring:message code="course.dayofweek"/></th>
			<th><spring:message code="course.time"/></th>
			<th><spring:message code="course.level"/></th>
			<th><spring:message code="course.academy"/></th>

			
		</tr>
	</thead>
	<tbody>
		<jstl:forEach items="${courses}" var="course">
			<tr>
				<td>
          <tr>${course.titulo}</tr>
          <tr>${course.estilo}</tr>
          <tr>${course.fechaInicio}</tr>
          <tr>${course.fechaFin}</tr>
          <tr>${course.diaSemana}</tr>
          <tr>${course.hora}:${course.minuto}</tr>
          <tr>${course.nivel}</tr>
          <tr>
            <a href="academy/viewAcademyByCourse.do?courseId=${course.id}">
              <spring:message code="course.academy.see"/>
            </a>
          </tr>
          <security:authorize access="hasRole('ACADEMIA')">
					<td>
						<a href="academy/course/edit.do?courseId=${course.id}"> <spring:message
						code="course.edit" />
						</a>
					</td>
		
				</security:authorize>


        </td>
			</tr>
		</jstl:forEach>
	</tbody>
</table>