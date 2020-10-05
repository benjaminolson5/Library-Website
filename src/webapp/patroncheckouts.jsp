<%@page import="com.cognixia.jump.model.BookCheckout"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.cognixia.jump.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ include file= "patronheader.jsp" %>

<div class="container">

	<% ArrayList<BookCheckout> bk = (ArrayList<BookCheckout>) request.getAttribute("checkedOut"); %>
	<% Iterator<BookCheckout> bkiter = bk.iterator(); %>

	<h3>Your Books</h3>
	<a class="btn btn-primary" href="patronHome.jsp" role="button">Back to Home</a><br>
	
	<div id="list">
		<% while(bkiter.hasNext()) {  %>
			<% BookCheckout book = bkiter.next(); %>
			<dl class="book">
				<dt><%= book.getIsbn() %></dt>
				<dd>Check Out Date: <%= book.getCheckedOut() %></dd>
				<dd>Due Date: <%= book.getDueDate() %></dd>
				<% if(book.getReturned() == null) { %>
					<dd style="color: red">CURRENTLY OUT</dd>
				<% } else { %>
					<dd style="color: green">Returned on: <%= book.getReturned() %></dd>
				<% } %>
			</dl>
			<% if(book.getReturned() == null) { %>
				<a class="btn btn-danger btn-sm" href="<%= request.getContextPath() %>/return?action&checkoutid=<%=book.getCheckId() %>" role="button">Return</a>
			<% } %>
		<% } %>
	</div>

</div>

<%@ include file= "footer.jsp" %>
