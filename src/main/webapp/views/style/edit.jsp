<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" prefix="c">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form id="styleForm" action="style/administrator/edit.do" modelAttribute="style">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="style.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="description">
		<spring:message code="style.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="style.save" />" />&nbsp; 
	<c:if test="${style.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="style.delete" />"
			onclick="return confirm('<spring:message code="style.confirm.delete" />')" />&nbsp;
	</c:if>
	<input type="button" name="cancel"
		value="<spring:message code="style.cancel" />"
		onclick="javascript: relativeRedir('style/administrator/list.do');" />
	<br />

</form:form>
