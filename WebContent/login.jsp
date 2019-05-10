<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<style type="text/css">
	
	.login-form {
		width: 350px;
    	margin: 60px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>

</head>
<body>
	<div class="login-form">
		<div class="row">
			<div class="center-block">
				<form action="login" method="post">
					<h2 class="text-center">Log in</h2>
					<div class="form-group">
            			<input type="text" name="name" class="form-control" placeholder="Username" required="required">
        			</div> 															
					<div class="form-group">
            			<input type="password" name="password" class="form-control" placeholder="Password" required="required">
        			</div>
        			<div class="form-group">
            			<input type="submit" class="btn btn-primary btn-block" value="Log in">
        			</div>
				</form>
			</div>
		</div>		
</div>
	
	</div>
</body>
</html>