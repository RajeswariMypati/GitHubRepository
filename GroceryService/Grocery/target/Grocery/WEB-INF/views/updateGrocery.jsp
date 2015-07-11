<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Grocery Store Portal</title>
</head>
<body>
<form:form method="post" action="../updateGrocery" commandName="grocery">

	<table>
	<tr>
		<td><form:label path="groceryId"><spring:message code="grocerystore.groceryId"/></form:label></td>
		<td><form:input readonly="true" path="groceryId" /></td> 
	</tr>	
	<tr>
		<td><form:label path="groceryName"><spring:message code="grocerystore.groceryName"/></form:label></td>
		<td><form:input readonly="true" path="groceryName" /></td> 
	</tr>
	<tr>
		<td><form:label path="groceryPrice"><spring:message code="grocerystore.groceryPrice"/></form:label></td>
		<td><form:input path="groceryPrice" /></td>
	</tr>
	<tr>
		<td><form:label path="groceryStock"><spring:message code="grocerystore.groceryStock"/></form:label></td>
		<td><form:input readonly="true" path="groceryStock" /></td>
	</tr>
	<tr>
		<td><form:label path="groceryUpdatedOn"><spring:message code="grocerystore.groceryUpdatedOn"/></form:label></td>
		<td><form:input readonly="true" path="groceryUpdatedOn" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="grocerystore.updateGrocery"/>"/>
		</td>
	</tr>
</table>	
</form:form>

</body>
</html>