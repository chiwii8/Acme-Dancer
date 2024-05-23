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
  name="estilos"
  requestURI="${requestURI}"
  id="row"
>
  <!-- Action links -->

  <security:authorize access="hasRole('ADMIN')">
    <display:column>
      <a href="estilo/administrador/edit.do?announcementId=${row.id}">
        <spring:message code="estilo.editar" />
      </a>
    </display:column>
  </security:authorize>

  <spring:message code="estilo.nombre" var="nombreHeader" />
  <display:column property="nombre" title="${nombreHeader}" sortable="true" />

  <spring:message code="estilo.descripcion" var="descripcionHeader" />
  <display:column
    property="descripcion"
    title="${descripcionHeader}"
    sortable="false"
  />
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
  <div>
    <a href="estilo/administrator/create.do">
      <spring:message code="estilo.create" />
    </a>
  </div>
</security:authorize>
