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

<form:form id="courseForm" action="academy/course/edit.do" modelAttribute="userAccount">
<form:label path="nombre">
		<spring:message code="actor.name" />:
	</form:label>
	<form:input path="nombre" />
	<form:errors cssClass="error" path="nombre" />
	<br />

	<form:label path="apellidos">
		<spring:message code="actor.surname" />:
	</form:label>
	<form:input path="apellidos" />
	<form:errors cssClass="error" path="apellidos" />
	<br />

    <form:label path="correo">
	<spring:message code="actor.email" />:
	</form:label>
	<form:input path="correo" />
	<form:errors cssClass="error" path="correo" />
	<br />

    <form:label path="telefono">
	<spring:message code="actor.phone" />:
	</form:label>
	<form:input path="telefono" />
	<form:errors cssClass="error" path="telefono" />
	<br />
    
    <form:label path="codigoPostal">
	<spring:message code="actor.postalcode" />:
	</form:label>
	<form:input path="codigoPostal" />
	<form:errors cssClass="error" path="codigoPostal" />
	<br />

    </form:form>