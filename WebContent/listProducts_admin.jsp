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
		$("#adminTable tbody").append(
				"<tr>"+
				"<td><input type='text' name='id' readonly/></td>"+
				"<td><input type='text' name='name' /></td>"+
				"<td><input type='text' name='price' /></td>"+
				"<td><img src='images/save.png' class='btnSave'><img src='images/delete.png' class='btnDelete'/></td>"+
				"</tr>"
		);
		
		$("tr:last").find(".btnSave").bind("click", save);		
		$("tr:last").find(".btnDelete").bind("click", del);
		
	};
	
	function save() {
		var par = $(this).parent().parent(); //tr
		var tdId = par.children("td:nth-child(1)");
		var tdName = par.children("td:nth-child(2)");
		var tdPrice = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");
		
		var name = tdName.children("input").val();
		var price = tdPrice.children("input").val();
		
		//TODO: 
		if (name == "" || price == "") {
			alert("Invalid Input!");
			return;
		}							
		
		var id = -1;
		$.post(
			"addProduct", 
			{
				name: name,
				price: price
			},
			function(text) {				
				id = parseInt(text)
				tdId.children("input").val(id);						
			});		
		
		//quick fix, need future improvement
		price_float = parseFloat(price)
		tdPrice.children("input").val(price_float);
			
		tdId.children("input").attr("readonly", true);
		tdName.children("input").attr("readonly", true);
		tdPrice.children("input").attr("readonly", true);		
		tdButtons.html('<input class="btnAdd" type="image" src="images/add.png">'
						+ '<input class="btnEdit" type="image" src="images/edit.png">'
						+ '<input class="btnDelete" type="image" src="images/delete.png">');

		tdButtons.find(".btnAdd").bind("click", add);
		tdButtons.find(".btnEdit").bind("click", edit);
		tdButtons.find(".btnDelete").bind("click", del); 
	}
	
	function save_edit() {
		var par = $(this).parent().parent(); //tr
		var tdId = par.children("td:nth-child(1)");
		var tdName = par.children("td:nth-child(2)");
		var tdPrice = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");
		
		var id = tdId.children("input").val();
		var name = tdName.children("input").val();
		var price = tdPrice.children("input").val();
		
		console.log("0 save edit: " + id + "|" + name + "|" + price);
		
		//TODO: 
		if (name == "" || price == "") {
			alert("Invalid Input!");
			return;
		}							
				
		$.post(
			"updateProduct", 
			{
				id: id,
				name: name,
				price: price
			},
			function(data) {				
				var json = JSON.parse(data);
				id = json.product.id;
				name = json.product.name;
				price = json.product.price;
				console.log("1 save edit: " + id + "|" + name + "|" + price);
			});		
		
		//quick fix, need future improvement
		price_float = parseFloat(price)
		tdPrice.children("input").val(price_float);
		
		tdId.children("input").val(id);
		tdName.children("input").val(name);		
			
		tdId.children("input").attr("readonly", true);
		tdName.children("input").attr("readonly", true);
		tdPrice.children("input").attr("readonly", true);	
		
		tdButtons.html('<input class="btnAdd" type="image" src="images/add.png">'
						+ '<input class="btnEdit" type="image" src="images/edit.png">'
						+ '<input class="btnDelete" type="image" src="images/delete.png">');

		tdButtons.find(".btnAdd").bind("click", add);
		tdButtons.find(".btnEdit").bind("click", edit);
		tdButtons.find(".btnDelete").bind("click", del); 
	}
	
	function edit() {
		
		var tr = $(this).closest('tr');
		var tdName = tr.children('td:nth-child(2)');
		var tdPrice = tr.children('td:nth-child(3)');
		var tdButtons = tr.children('td:nth-child(4)');
		
		var inputName = tdName.find('input');				
		var inputPrice = tdPrice.find('input');
		
		/* console.log(inputName.val() + "|" + inputPrice.val()); */
		inputName.attr("readonly", false);
		inputPrice.attr("readonly", false);
		
		tdButtons.html("<img src='images/save.png' class='btnSave'><img src='images/delete.png' class='btnDelete'/>");
		
		tdButtons.find('.btnSave').bind('click', save_edit);
		tdButtons.find('.btnDelete').bind('click', del);
		
	};
	
	function del() {
		var tr = $(this).parent().parent(); 
		var tdId = tr.children("td:nth-child(1)");
		var id = tdId.children("input").val();				
		
		$.post(
			"deleteProduct",
			{id: id},
			function(data) {
								
				$("#successMessage").fadeIn(1000, function() {
					$("#successMessage").attr("hidden", false);
					$("#successMessage").html(data);
				});
       	   		$("#successMessage").fadeOut(1000,function(){
	       	   		$("#successMessage").attr("hidden", true);					 
        	    });	   
			}
		);
		
		tr.remove();		
	};
</script>
</head>
<body>
	<div align="center" style="height: 20px; margin: 20px;">
		<span style="color: Chartreuse" id="successMessage" hidden>
			
		</span>
	</div>
	<c:if test="${!empty user}">
		<div align="center">
			<h2>Welcome ${user.name}</h2>
		</div>
	</c:if>
	
	<div align="center" style="margin: 0 auto; text-align:center;">
		<table id="adminTable" style="width: 60%;" class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
					<th>Management</th>
				</tr>
			</thead>
			<tbody>
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
			</tbody>
		</table>


</body>
</html>