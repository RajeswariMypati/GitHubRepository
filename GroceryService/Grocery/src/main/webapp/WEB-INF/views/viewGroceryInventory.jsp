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
<form:form method="post" action="./getGroceryListByName"  commandName="grocery">
<table align="center" cellpadding="1" cellspacing="1" style="background-color:#F8F8FF;width: 500px;border:0px solid black;">
	<caption><h1>Grocery Inventory</h1></caption>
	<tbody>
	<tr><td>
			<table>
				<tr>
					<td align="center"><input type="text" name="groceryName" /></td> 
					<td align="left"><input type="submit" value="<spring:message code="grocerystore.filterGroceryDetails"/>"/></td>
				</tr>
				<tr><td colspan='2'/></tr>
				<tr><td colspan='2'/></tr>
					<c:if  test="${!empty groceryInvList}">
					<tr><td colspan='2'>
					<table border="1" align="center" style="border:1px border-style:groove;" cellspacing="0" cellpadding="0">
					<tr>
						<th><spring:message code="grocerystore.groceryId"/></th>
						<th><spring:message code="grocerystore.groceryName"/></th>
						<th><spring:message code="grocerystore.groceryPrice"/></th>
						<th><spring:message code="grocerystore.groceryStock"/></th>
						<th><spring:message code="grocerystore.groceryUpdatedOn"/></th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${groceryInvList}" var="grocery">
						<tr>
							<td>${grocery.groceryId}</td>		
							<td>${grocery.groceryName}</td>
							<td>${grocery.groceryPrice}</td>
							<td>${grocery.groceryStock}</td>
							<td>${grocery.groceryUpdatedOn}</td>
							<td><a href="showUpdateGrocery/${grocery.groceryId}">Update Grocery</a></td>
						</tr>
					</c:forEach>
					</table>
					</td></tr>
					</c:if>
				</td></tr>
			</table>
	</td></tr>				
	</tbody>
</table>

</form:form>

</body>
</html>