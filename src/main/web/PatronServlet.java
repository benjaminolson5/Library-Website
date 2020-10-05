package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookCheckoutDAO;
import com.cognixia.jump.dao.BookDAO;
import com.cognixia.jump.dao.PatronDAO;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Patron;

@WebServlet("/")
public class PatronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//include feature about who is logged in
	private BookDAO bookDAO;
	private BookCheckoutDAO checkoutDAO;
	private PatronDAO patronDAO;
	
	public void init() {
		//instantiate book on initialization
		bookDAO = new BookDAO();
		checkoutDAO = new BookCheckoutDAO();
		patronDAO = new PatronDAO();
	}

	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/profile":
			profile(request, response);
			break;
		case "/checkout":
			checkoutBook(request, response);
			break;
		case "/viewCheckedOutBooks":
			viewCheckedOutBooks(request, response);
			break;
		case "/return":
			returnBook(request, response);
			break;
		case "/viewAllBooks":
			viewAllBooks(request, response);
			break;
		case "/viewAvailableBooks":
			viewAvailableBooks(request, response);
			break;
		default:
			response.sendRedirect("patronHome.jsp");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void profile(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//route to patronHome
		RequestDispatcher dispatcher = request.getRequestDispatcher("patronHome.jsp");
		
		dispatcher.forward(request, response);
	} 
	
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int patronId = Integer.parseInt(request.getParameter("id"));
		String isbn = request.getParameter("isbn");
//		String username = request.getParameter("username");
		Book book = bookDAO.getBookByISBN(isbn);
		Patron patron = patronDAO.getPatronById(patronId);
		
		checkoutDAO.addCheckoutLog(book, patron);
		
		response.sendRedirect("patronHome.jsp");
	}
	
	private void returnBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int checkoutId = Integer.parseInt(request.getParameter("checkoutid"));
		BookCheckoutDAO.returnCheckout(checkoutId);
		
		/*String bookName = request.getParameter("bookName");
		Book returning = bookDAO.getBookByISBN(bookName);
		
		bookDAO.bookReturned(returning, patron);*/
		
		response.sendRedirect("patronHome.jsp");
	}
	
	private void viewCheckedOutBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int patronId = Integer.parseInt(request.getParameter("id"));
		List<BookCheckout> allBooks = patronDAO.getAllCheckouts(patronId);
		System.out.println("called, patronDAO = " + allBooks);
		
		request.setAttribute("checkedOut", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("patroncheckouts.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void viewAllBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Book> allBooks = bookDAO.getAllBooks(false);
		System.out.println("called, bookDAO = " + allBooks);
		
		request.setAttribute("allBooks", allBooks);
		request.setAttribute("canCheckout", 0);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void viewAvailableBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<Book> availableBooks = bookDAO.getAllBooks(true);
		System.out.println("called, bookDAO = " + availableBooks);
		
		request.setAttribute("allBooks", availableBooks);
		request.setAttribute("canCheckout", 1);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		
		dispatcher.forward(request, response);
	}
}
