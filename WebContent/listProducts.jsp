<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cart.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listProducts.jsp</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<script>
$(function(){

	$("input.addCartButton").removeAttr("disabled");
	$("input.addCartButton").click(function(){
		$(this).prop("disabled","true");
		var button = $(this);
		var pid = $(this).attr("pid");				
		var number = $("input.number[pid="+pid+"]").val();
		var page = "addOrderItem";		
		
      	$.get(
	           page,
	           {"num":number,"pid":pid},
	           function(result){
	        	   $("#addCartSuccessMessage").fadeIn(1200);
	        	   $("#addCartSuccessMessage").fadeOut(1200,function(){
	        		   button.removeAttr("disabled") ;   
	        	   });	   
	           }
	    );
		
	});

	$("#addCartSuccessMessage").hide();

});	
</script>
<style>
	td {
		width: 50px;
		align: center;
	}
	
	th {
		text-align: center;
	}
	
	table {
		margin: 60px auto;
	}
	
	input.form-control {
		width: 50%;
		
	}
</style>
</head>
<body>
	<div align="center" style="height: 20px; margin: 20px;">
		<span style="color: Chartreuse" id="addCartSuccessMessage">Item
			Added to Cart</span>
	</div>

	<%
		User user = (User) session.getAttribute("user");

		if (user != null) {
	%>
	<div align="center"><h3>User Name: ${user.name}</h3></div>
	<%
		}
	%>
	<div align="center" style="margin: 0 auto; text-align:center;">
		<table style="width: 60%;" class="table table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Buy Now!</th>
			</tr>
			<c:forEach items="${products}" var="product" varStatus="st">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>
						<div class="form-group form-inline">
							<label class="control-label"> #: </label> 
							<input class="number form-control" type="text" value="1" name="num" 
								   pid="${product.id}"> 
							<input pid="${product.id}" class="addCartButton btn btn-primary" type="submit"
							 		   value="Add To Cart">
		 		   		</div>
					</td>
				</tr>
			</c:forEach>
	
			<tr>
				<td align="center" colspan="4">
					<a href="listOrderItem">My Cart</a>
				</td>
			</tr>
		</table>
	
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>