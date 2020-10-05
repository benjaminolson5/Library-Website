package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginDAO ldao;
	
	@Override
	public void init() throws ServletException {
		ldao = new LoginDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean loginStatus = false;
		int userId;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isLibrarian = Boolean.parseBoolean(request.getParameter("librarianCheck"));
		
		userId = ldao.login(username, password, isLibrarian);
		if(userId != -1) {
			loginStatus = true;
		}
		
		request.setAttribute("loginStatus", loginStatus);
		request.setAttribute("userId", userId);
		
		RequestDispatcher dispatcher = null;
		
		if(userId == -1) {
			request.setAttribute("loginStatus", "-1");
			dispatcher = request.getRequestDispatcher("login.jsp");
		} else if(isLibrarian) {
			dispatcher = request.getRequestDispatcher("librarianHome.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("patronHome.jsp");
		}
				
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
