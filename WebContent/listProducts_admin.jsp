<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listProducts_admin.jsp</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

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

<script type="text/javascript">
	$(function() {
		
		$(".btnAdd").bind("click", add);
		$(".btnEdit").bind("click", edit);
		$(".btnDelete").bind("click", del);
		
	});
	
	function add() {
		
	};
	
	function edit() {
		console.log('haha');
		let tr = $(this).closest('tr');
		let inputName = tr.children('td:nth-child(2)').find('input');
		let inputPrice = tr.children('td:nth-child(3)').find('input');
		
		console.log(inputName.val() + "|" + inputPrice.val());
		inputName.attr("readonly", false);
		inputPrice.attr("readonly", false);
		
		inputName.keypress((event) => {
			var keycode = (event.keyCode ? event.keyCode : event.which);
		    if(keycode == '13'){ //press enter
		        alert('You pressed a "enter" key in textbox'); 
		        inputName.attr("readonly", true);
		    }			
		});
		
		inputPrice.keypress((event) => {
			var keycode = (event.keyCode ? event.keyCode : event.which);
		    if(keycode == '13'){ //press enter
		        alert('You pressed a "enter" key in textbox'); 
		        inputPrice.attr("readonly", true);
		    }			
		});
		
	};
	
	function del() {
		
	};
</script>
</head>
<body>
	<c:if test="${!empty user}">
		<div align="center">
			<h2>Welcome ${user.name}</h2>
		</div>
	</c:if>
	
	<div align="center" style="margin: 0 auto; text-align:center;">
		<table style="width: 60%;" class="table table-striped table-hover">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Management</th>
			</tr>
			<c:forEach items="${products}" var="product" varStatus="st">
				<tr>
					<td><input type="text" name="id" value="${product.id}" readonly></td>
					<td><input type="text" name="name" value="${product.name}" readonly></td>
					<td><input type="text" name="price" value="${product.price}" readonly></td>
					<td>
						<input class="btnAdd" type="image" src="images/add.png">
						<input class="btnEdit" type="image" src="images/edit.png">
						<input class="btnDelete" type="image" src="images/delete.png">						
					</td>
				</tr>
			</c:forEach>	
		</table>


</body>
</html>