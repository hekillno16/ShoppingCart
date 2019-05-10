<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listOrderItem.jsp</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">

	table {
		width: 60%;
		align: center;
	}
	
	td {
		width: 50px;
		align: center;
	}
	
	th {
		text-align: center;
	}
	
</style>
 
</head>
<body>
	<h1 align="center">Shopping Cart</h1>
	<div style="width:50%; margin: 0 auto; text-align:center;">
		<table class="table table-striped table-hover" align="center" >
			<tr>
				<th>Item Name</th>
				<th>Price</th>
				<th>Numbers</th>
				<th>Total</th>
				<th>Delete</th>
			</tr>
			
			<c:forEach items="${oilst}" var="oi" varStatus="st">
				<tr>
					<td>${oi.product.name}</td>
					<td>${oi.product.price}</td>
					<td>${oi.num}</td>
					<td>${oi.product.price * oi.num}</td>
					<td>
						<form class="form-group form-inline" method="post" action="deleteOrderItem" >						
							<label class="control-label">#:</label>
							<input class="form-control"type="text" class="form-control" name="num" value="1">
							<input type="hidden" name="id" value="${oi.id}">
							<input class="btn btn-primary" type="submit" value="DELETE">	
						</form>				
					</td>
				</tr>
			</c:forEach>	
			<c:if test="${!empty oilst}">
				<tr>
					<c:set var="total" value="${0}"/>
					<c:forEach items="${oilst}" var="oi" varStatus="st">
						<c:set var="total" value="${total + oi.product.price * oi.num}"/>
					</c:forEach>					
					<td colspan="5" align="right">
						<h5>Total: ${total}</h3>
					</td>		
				</tr>
				<tr>
					<td colspan="5" align="right">
						<a href="createOrder">Check Out</a>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	
	
	
	<%@ include file="footer.jsp" %>
</body>
</html>