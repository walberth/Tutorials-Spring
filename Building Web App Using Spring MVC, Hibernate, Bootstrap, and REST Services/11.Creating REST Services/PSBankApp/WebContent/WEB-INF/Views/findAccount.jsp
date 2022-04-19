<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PS Bank: Search Account Holder</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row">
			<div class="col-12">
				<p class="h1">Search Form</p>
			</div>
		</div>

		<div class="row">
			<div class="col-8">
				<form:form cssClass="form-horizontal" role="form" id="accForm">
					<div class="form-group row">
						<Label for="accountNo" class="col-2 col-form-label">Account
							No:</Label>
						<div class="col-8">
							<input type="text" class="form-control"
								placeholder="Enter Account No" name="accountNo" id="accountNo" />
						</div>
						<div class="col-2">
							<button type="submit" id="bth-search" class="btn btn-primary">
								Search</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>

		<div class="row" id="divResults">
			<div class="col-8">
				<div class="card">
					<div class="card-header bg-success text-white">
						<h3>Search Results</h3>
					</div>
					<div class="card-body">
						<div id="accountNo"></div>
						<div id="accountHolderName"></div>
						<div id="accountType"></div>
						<div id="balance"></div>
						<div id="psCode"></div>
						
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		

		<div class="row" id="divError">
			<div class="col-8">
				<p class="alert alert-danger">Requested Account Holder Details
					Not Found</p>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

	<script>
	$(document).ready(function($) {
		$('#divResults').hide();
		$('#divError').hide();

		$("#accForm").submit(function(event) {
			enableSearchButton(false);
			event.preventDefault();
			searchViaAjax();
		});
	});
	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}
	function searchViaAjax() {
		var accnt = {}
		accnt["accountNo"] = $("#accountNo").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "http://localhost:8080/PSBankApp/account",
			data : JSON.stringify(accnt),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
			        	display(data);
				  },
			error : function(e) {
					$('#divResults').hide();
					$('#divError').show();
				},
			done : function(e) {
					enableSearchButton(true);
				}
		});
	}
	
	
	function display(data) {
		$('#accountNo').html("Account Number:" + data["accountNo"]);
		$('#accountHolderName').html("Holder name:" + data["accountHolderName"]);
		$('#balance').html("Balance:" + data["balance"]);
		$('#accountType').html("Account Type :" + data["accountType"]);
		$('#psCode').html("Security Code :" + data["psCode"]);
		$('#divError').hide();
		$('#divResults').show();
	}
	
	</script>
</body>
</html>