<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form id="courseForm" action="academy/course/edit.do" modelAttribute="course">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="titulo">
		<spring:message code="course.title" />:
	</form:label>
	<form:input path="titulo" />
	<form:errors cssClass="error" path="titulo" />
	<br />

	<form:label path="fechaInicio">
		<spring:message code="course.startdate" />:
	</form:label>
	<form:input path="fechaInicio" />
	<form:errors cssClass="error" path="fechaInicio" />
	<br />

    <form:label path="fechaFin">
	<spring:message code="course.enddate" />:
	</form:label>
	<form:input path="fechaFin" />
	<form:errors cssClass="error" path="fechaFin" />
	<br />

    <form:label path="diaSemana">
	<spring:message code="course.dayofweek" />:
	</form:label>
	<form:input path="diaSemana" />
	<form:errors cssClass="error" path="diaSemana" />
	<br />
    
    <form:label path="hora">
	<spring:message code="course.hour" />:
	</form:label>
	<form:input path="hora" />
	<form:errors cssClass="error" path="hora" />
	<br />
    
    <form:label path="minuto">
	<spring:message code="course.minutes" />:
	</form:label>
	<form:input path="minuto" />
	<form:errors cssClass="error" path="minuto" />
	<br />

    <form:label path="nivel">
	<spring:message code="course.level" />:
	</form:label>
	<form:input path="nivel" />
	<form:errors cssClass="error" path="nivel" />
	<br />



	<input type="submit" name="save"
		value="<spring:message code="style.save" />" />&nbsp; 
	<jstl:if test="${course.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="style.delete" />"
			onclick="return confirm('<spring:message code="course.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="course.cancel" />"
		onclick="javascript: relativeRedir('administrator/style/list.do');" />
	<br />

</form:form>