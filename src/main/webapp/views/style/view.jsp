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

<h1>
	<jstl:out value="${style.nombre}" />
</h1>

<br />

<p>
	<spring:message code="style.description.name" />: 
	<jstl:out value="${style.descripcion}" />
</p>

<br />
<h3>Imagenes</h3>
<jstl:choose>
	<jstl:when test="${not empty style.imagenes}">
<jstl:forEach var="image" items="${style.imagenes}">
	<img src="${image}">
	<br />
</jstl:forEach>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="style.view.noimage"/>
	</jstl:otherwise>
</jstl:choose>
<br />

<h3>Videos</h3>
<jstl:choose>
	<jstl:when test="${not empty style.videos}">
		<jstl:forEach var="video" items="${style.videos}">
	<iframe width="560" height="315" src="${video}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
	<br />
</jstl:forEach>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="style.view.novideo"/>
	</jstl:otherwise>
</jstl:choose>
