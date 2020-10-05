<%@page import="java.util.Iterator"%>
<%@page import="com.cognixia.jump.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ include file= "patronheader.jsp" %>

<div class="container">

	<% ArrayList<Book> al = (ArrayList<Book>) request.getAttribute("allBooks"); %>
	<% Iterator<Book> iterator = al.iterator(); %>

	<h3>Books of the Library!</h3>
	<a class="btn btn-primary" href="patronHome.jsp" role="button">Back to Home</a><br>
	
	<div id="list">
		<% while(iterator.hasNext()) {  %>
			<% Book book = iterator.next(); %>
			<dl class="book">
				<dt><%= book.getTitle() %></dt>
				<dd>description: <%= book.getDescr() %></dd>
			</dl>
			<% if((int) request.getAttribute("canCheckout") == 1) {  %>
				<a class="btn btn-primary btn-sm" href="<%= request.getContextPath() %>/checkout?action&id=<%=patron.getPatId() %>&isbn=<%=book.getIsbn() %>" role="button">Checkout</a><br>
			<% } %>
		<% } %>
	</div>

</div>

<%@ include file= "footer.jsp" %>
