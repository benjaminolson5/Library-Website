package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.PatronDAO;
import com.cognixia.jump.model.Patron;

/**
 * Servlet implementation class CreateUser
 */
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PatronDAO patronDAO;
	
	@Override
	public void init() throws ServletException {
		patronDAO = new PatronDAO();
	}
	
	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Patron newPatron = new Patron(firstName, lastName, username, password, false);
		
		patronDAO.addPatron(newPatron);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("patronHome.jsp");
		dispatcher.forward(request, response);
	}

}
