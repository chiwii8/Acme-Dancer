<%--
 * footer.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />

<hr />
<a style="font-weight:bold;
		  text-decoration:none;
		  color:black;"
	href="privacy/polite.do">
		   <spring:message code="master.page.policy" />
</a>
<br/>

<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> Acme Dancer Co., Inc.</b>