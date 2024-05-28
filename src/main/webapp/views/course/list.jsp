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
	


	<spring:message code="course.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
  <spring:message code="course.style" var="styleHeader" />
	<display:column property="style" title="${styleHeader}" sortable="true" />
  
  <spring:message code="course.style" var="styleHeader" />
	<display:column property="style" title="${styleHeader}" sortable="true" />
  
  <spring:message code="course.style" var="styleHeader" />
	<display:column property="style" title="${styleHeader}" sortable="true" />

  <display:column>
    <a href="academy"><spring:message code="course.academy.see"/></a>
  </display:column>
    	<!-- Attributes -->
	<security:authorize access="hasRole('ACADEMIA')">
    <display:column>
      <a href="/academia/edit.do?estiloId=${row.id}">
        <spring:message code="estilo.editar" />
      </a>
    </display:column>
  </security:authorize>
</display:table>