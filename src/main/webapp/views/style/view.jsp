<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" prefix="c">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

  <security:authorize access="hasRole('ADMIN')">
    <div class ="fNiv">
        <a href="estilo/administrador/edit.do?estiloId=${row.id}">
          <spring:message code="estilo.edit" />
        </a>
    </div>
    </display:column>
  </security:authorize>

  <br/>
  <h1><c:out value="style.nombre"/></h1>

  <div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li><a class="fNiv" href="course/listByEstiloId.do"><spring:message code="style.see.course" /></a></li>
    </ul>
  </div>

  <br/>
  <br/>
  
  <p>
    <spring:message code="style.description.name"/>
    <c:out value="style.descripcion"/>
  </p>

  <br/>
  <h3>Imágenes</h3>
  <c:forEach var="image" items="style.images">
        <img src="${image}">
        <br/>
  </c:forEach>
  <br/>

  <h3>Vídeos</h3>
  <c:forEach var="video" items="style.videos">
        <iframe width="560" height="315" src="${videos}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        <br/>
    </c:forEach>

<!-- Action links -->

<security:authorize access="hasRole('ADMIN')">
  <div>
    <a href="style/administrator/create.do">
      <spring:message code="style.create" />
    </a>
  </div>
</security:authorize>