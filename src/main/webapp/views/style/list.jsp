<%@page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@taglib prefix="jstl"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@taglib prefix="form"
uri="http://www.springframework.org/tags/form"%> <%@taglib prefix="security"
uri="http://www.springframework.org/security/tags"%> <%@taglib prefix="display"
uri="http://displaytag.sf.net"%>

<display:table
  pagesize="5"
  class="displaytag"
  keepStatus="true"
  name="styles"
  requestURI="/list.do"
  id="row"
>
  <!-- Action links -->

  <security:authorize access="hasRole('ADMIN')">
    <display:column>
      <a href="estilo/administrador/edit.do?estiloId=${row.id}">
        <spring:message code="estilo.edit" />
      </a>
    </display:column>
  </security:authorize>

  <spring:message code="style.name" var="nameHeader" />
  <display:column property="nombre" title="${nameHeader}" sortable="false" />

  <spring:message code="style.description" var="descripcionHeader" />
  <display:column
    property="descripcion"
    title="${descripcionHeader}"
    sortable="false"
  />
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
  <div>
    <a href="style/administrator/create.do">
      <spring:message code="style.create" />
    </a>
  </div>
</security:authorize>