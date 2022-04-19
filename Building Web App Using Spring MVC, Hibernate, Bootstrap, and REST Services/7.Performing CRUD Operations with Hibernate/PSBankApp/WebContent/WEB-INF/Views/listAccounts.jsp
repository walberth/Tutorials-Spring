<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PS Bank Account Holder Details</title>
</head>
<body>
	<table border="1" width="100%">
		<thead>
			<tr>
				<th><spring:message code="lbl.accountNo" /> </th>
				<th><spring:message code="lbl.accountHolderName" /> </th>
				<th><spring:message code="lbl.balance" /> </th>
				<th> &nbsp; </th>
				<th> &nbsp; </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${accounts}">
				<c:url var="updateLink" value="/edit">
					<c:param name="accountNo" value="${account.accountNo}" />
				</c:url>
				
				<c:url var="deleteLink" value="/delete">
					<c:param name="accountNo" value="${account.accountNo}" />
				</c:url>
					<tr>
						<td>${account.accountNo} </td>
						<td>${account.accountHolderName} </td>
						<td>${account.balance} </td>
						<td><a href="${updateLink}">Edit</a></td>
						<td><a href="${deleteLink}"
						onClick="if(!(confirm('Are you sure to delete'))) return false">
						Delete</a></td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>