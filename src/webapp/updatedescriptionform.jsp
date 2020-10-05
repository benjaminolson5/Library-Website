<%@ include file= "librarianheader.jsp" %>

<div class="container">



	<form action="LibrarianServlet" method="POST">
		<fieldset>
			<legend>Update Description</legend>
			
			<input type="hidden" id="met" name="met" value="descr">
 			<div class="form-row">
 				<div class="col-3">
					<label for="isbn">ISBN:</label>
					<input type="text" class="form-control" id="isbn" name="isbn" required><br>
 				</div>
 			</div>
 			
 			<div class="form-row">
 				<div class="col-3">
					<label for="descr">Description:</label>
					<input type="text" class="form-control" id="descr" name="descr" required><br>
 				</div>
 			</div>
 			
  			<div class="form-row">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Update</button>
				<a class="btn btn-outline-secondary my-2 my-sm-0" href="librarianHome.jsp" role="button" style="margin-left: 10px;">Cancel</a>
			</div>
		</fieldset>
	</form>

</div>

<%@ include file= "footer.jsp" %>
