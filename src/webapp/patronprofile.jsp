<%@ include file= "patronheader.jsp" %>

<div class="container">

 	<div class="form-group">
		<h1>Patron: <em><%= patron.getfName() + " " + patron.getlName() %></em></h1>
	</div>
	
 	<div class="form-group">
		<h4>Username: <em><%= patron.getUser() %></em></h4>
	</div>
	
 	<div class="btn-group-vertical">
		<a class="btn btn-dark" href="<%= request.getContextPath() %>/list" role="button">Change Name</a>
		<a class="btn btn-dark" href="<%= request.getContextPath() %>/list" role="button">Change Username</a>
		<a class="btn btn-dark" href="<%= request.getContextPath() %>/list" role="button">Change Password</a>
		<a class="btn btn-dark" href="<%= request.getContextPath() %>" role="button">Logout</a>
	</div>

</div>

<%@ include file= "footer.jsp" %>
