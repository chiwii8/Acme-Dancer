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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="cursos" requestURI="curso/list.do" id="row">
	
	<!-- Attributes -->
	<security:authorize access="hasRole('ACADEMIA')">
    <display:column>
      <a href="estilo/academia/edit.do?estiloId=${row.id}">
        <spring:message code="estilo.editar" />
      </a>
    </display:column>
  </security:authorize>
	<spring:message code="course.Name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

    ////TODO: Añadir un botón que nos permita entrar dentro de la academia para ver los
    /// datos y demás
</display:table>