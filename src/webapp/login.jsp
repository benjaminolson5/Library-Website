<%@ include file= "header.jsp" %>

<div class="container">

	
	<form action="LoginServlet" method="POST">
		<fieldset>
			<legend>Login</legend>
			
			<% int login = 0; if(request.getAttribute("loginStatus") != null) { login = Integer.parseInt((String)request.getAttribute("loginStatus")); }%>
			<% if(login < 0) { %> 
 				<div class="form-row">
 					<div class="col-3">
							<div class="alert alert-danger" role="alert">Invalid Login. Try Again.</div> 
 					</div>
 				</div>
			<% }  %>
			
 			<div class="form-row">
 				<div class="col-3">
					<label for="username">Username:</label>
					<input type="text" class="form-control" id="username" name="username" required><br>
 				</div>
 			</div>
  			<div class="form-row">
    			<div class="col-3">
					<label for="password">Password:</label>
					<input type="password" class="form-control" id="password" name="password" required><br>
 				</div>
  			</div>
  			<div class="form-group">
  				<div class="form-check">
  					<input class="form-check-input" type="checkbox" value="true" id="librarianCheck" name="librarianCheck">
  					<label class="form-check-label" for="librarianCheck">Librarian</label>
				</div>
			</div>
  			<div class="form-row">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
				<a class="btn btn-outline-secondary my-2 my-sm-0" href="<%= request.getContextPath() %>" role="button" style="margin-left: 10px;">Cancel</a>
			</div>
		</fieldset>
	</form>
	
	<br>
	<h5>Don't have an account?</h5>
	<a class="btn btn-primary btn-sm" href="createaccount.jsp" role="button">Create Account</a>
</div>

<%@ include file= "footer.jsp" %>
