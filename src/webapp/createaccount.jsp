<%@ include file= "header.jsp" %>

<div class="container">

	<form action="CreateUserServlet" method="POST">
		<fieldset>
			<legend>Create an Account</legend>
			<div class="form-row">
 				<div class="col-3">
					<label for="first">First Name:</label>
					<input type="text" class="form-control" id="first" name="first" required><br>
 				</div>
 				<div class="col-3">
					<label for="last">Last Name:</label>
					<input type="text" class="form-control" id="last" name="last" required><br>
 				</div>
 			</div>
			
			<div class="form-row">
 				<div class="col-3">
					<label for="username">Username:</label>
					<input type="text" class="form-control" id="username" name="username" required><br>
 				</div>
 				<div class="col-3">
					<label for="password">Password:</label>
					<input type="password" class="form-control" id="password" name="password" required><br>
 				</div>
 			</div>
			
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Create</button>
			<a class="btn btn-outline-secondary my-2 my-sm-0" href="<%= request.getContextPath() %>" role="button" style="margin-left: 10px;">Cancel</a>
		</fieldset>
	</form>
	
	<br>
	<h5>Already have an account?</h5>
	<a class="btn btn-primary btn-sm" href="login.jsp" role="button">Login</a>
</div>

<%@ include file= "footer.jsp" %>
