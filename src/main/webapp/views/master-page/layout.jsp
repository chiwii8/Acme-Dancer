<%-- * layout.jsp * * Copyright (C) 2018 Universidad de Sevilla * * The use of
this project is hereby constrained to the conditions of the * TDG Licence, a
copy of which you may download from * http://www.tdg-seville.info/License.html
--%> <%@page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@taglib prefix="form"
uri="http://www.springframework.org/tags/form"%> <%@taglib prefix="jstl"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib prefix="tiles"
uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es-en">
  <head>
    <base
      href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"
    />

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

    <link rel="shortcut icon" href="favicon.ico" />
    
    <script type="text/javascript" src="scripts/cookies.js"></script>
    <script type="text/javascript" src="scripts/jquery.js"></script>
    <script type="text/javascript" src="scripts/jquery-ui.js"></script>
    <script type="text/javascript" src="scripts/jmenu.js"></script>

    <link rel="stylesheet" href="styles/common.css" type="text/css" />
    <link
      rel="stylesheet"
      href="styles/jmenu.css"
      media="screen"
      type="text/css"
    />
    <link rel="stylesheet" href="styles/displaytag.css" type="text/css" />

    <title><tiles:insertAttribute name="title" ignore="true" /></title>

    <script type="text/javascript">
      $(document).ready(function () {
        $("#jMenu").jMenu();
      });

      function askSubmission(msg, form) {
        if (confirm(msg)) form.submit();
      }

      function relativeRedir(loc) {
        var b = document.getElementsByTagName("base");
        if (b && b[0] && b[0].href) {
          if (
            b[0].href.substr(b[0].href.length - 1) == "/" &&
            loc.charAt(0) == "/"
          )
            loc = loc.substr(1);
          loc = b[0].href + loc;
        }
        window.location.replace(loc);
      }
    </script>
  </head>

  <body>
    <div>
      <tiles:insertAttribute name="header" />
    </div>
    <div>
      <h1>
        <tiles:insertAttribute name="title" />
      </h1>
      <tiles:insertAttribute name="body" />
      <jstl:if test="${message != null}">
        <br />
        <span class="message"><spring:message code="${message}" /></span>
      </jstl:if>
    </div>
    <div>
      <tiles:insertAttribute name="footer" />
    </div>
    <div id="CookiesBox" style="display: block;
  box-shadow: 0px 0px 5px 5px #808080;
  background-color: white;
  color: black;
  padding: 10px;
  margin-left: -15px;
  margin-right: -15px;
  margin-bottom: 0px;
  position: fixed;
  top: 0px;
  width: 100%;">
	<button onclick="acceptCookies()"><spring:message code="cookies.accept"/></button>
	<button onclick="declineCookies()"><spring:message code="cookies.decline"/></button>
	<p><spring:message code="cookies.message"/> <a href="privacy/polite.do"><spring:message code="cookies.web"/></a></p>
</div>
  </body>
</html>
