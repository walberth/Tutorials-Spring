<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="lbl.title" /></title>
<style>
	.error{
		color:red
	}
</style>
</head>
<body>
<h2>Create New Account</h2>
<form:form action="saveAccount" modelAttribute="account">
<table>
	<tr>
		<td><spring:message code="lbl.accountNo" />: </td>
		<td><form:input path="accountNo" size="30" />
		<form:errors path="accountNo" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.accountHolderName" />: </td>
		<td><form:input path="accountHolderName" size="30" />
		<form:errors path="accountHolderName" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.balance" />: </td>
		<td><form:input path="balance" size="30" />
		<form:errors path="balance" cssClass="error" />
		</td>
	</tr> 
	<tr>
		<td><spring:message code="lbl.accountType" />: </td>
		<td><form:select path="accountType">
		<form:option value="">Select Account Type</form:option>
		<form:option value="SAVINGS">Savings </form:option>
		<form:option value="CURRENT">Current</form:option>
		</form:select>
		<form:errors path="accountType" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.dob" />: </td>
		<td><form:input path="dateOfBirth" size="30" />
		<form:errors path="dateOfBirth" cssClass="error" />
		</td>
	</tr>
	<tr>
		<td><spring:message code="lbl.psCode" />: </td>
		<td><form:input path="psCode" size="30" />
		<form:errors path="psCode" cssClass="error" />
		</td>
	</tr> 
	
	<tr>
		<td colspan="2" align="center">
		<input type="submit" value="Create Account" name="btnSubmit" /> </td>
	</tr>
</table>
</form:form>
</body>
</html>