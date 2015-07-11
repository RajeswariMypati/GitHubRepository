<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Welcome to Online Grocery Store!</h2>

<form:form method="GET" action="/getGroceryList">
<table>
    <tr><td>
        <a href="<c:url value="/getGroceryList"/>">Grocery List</a>
    </td></tr>
	<tr><td/></tr>
    <tr><td/></tr>
    <tr><td>
        <a href="<c:url value="showGroceryUpload"/>">Upload Grocery</a>
    </td></tr> 
</table>  
</form:form>
</body>
</html>
