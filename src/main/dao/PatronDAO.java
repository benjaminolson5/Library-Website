package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Patron;

public class PatronDAO {
	
	private Connection conn = ConnectionManager.getConnection();
	
	//create operations
	//WORKING
	public boolean addPatron(Patron patron) {
		try (Statement stmt = conn.createStatement()) {
			int added = stmt.executeUpdate("INSERT INTO patron(first_name, last_name, username, password)"
					+ "					VALUES('" + patron.getfName() + "', '" + patron.getlName() + "', '"
					+ patron.getUser() + "', '" + patron.getPass() + "')");

					if (added > 0) {
						System.out.println("New patron: " + patron.getfName() + " " + patron.getlName() + " added to the library");
						return true;
					} else {
						System.out.println("Failed to add patron " + patron.getfName() + " " + patron.getlName() + " to the library");
						return false;
					}
		} catch (SQLException e) {
			System.out.println("Failed to add patron " + patron.getfName() + " " + patron.getlName() + " to the library");
			return false;
		}
	}
	
	//read operations
	//WORKING
	public Patron getPatronById(int patronId) {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM patron WHERE patron_id = ?")) {
			pstmt.setInt(1, patronId);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			
			Patron byId = new Patron(rs.getString(2), 
					rs.getString(3), 
					rs.getString(4),
					rs.getString(5),
					rs.getBoolean(6)
					);
			
			byId.setPatId(rs.getInt(1));
			
			rs.close();
			return byId;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//past and current
	//WORKING
	public List<BookCheckout> getAllCheckouts(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "SELECT * FROM book_checkout WHERE patron_id = ?"))	
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery(); 

		
			List<BookCheckout> checkoutList = new ArrayList<>();
//			BookCheckoutDAO bcdao = new BookCheckoutDAO();
			
			while (rs.next()) {
				
				int checkoutId = rs.getInt(1);
				
				checkoutList.add(BookCheckoutDAO.getCheckoutLogById(checkoutId));
			}
//			System.out.println(checkoutList);
			return checkoutList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//update operations
	//WORKING
	public boolean updateName(Patron patron, String fname, String lname) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "UPDATE patron SET first_name = ?, last_name = ?"
				+ " WHERE patron_id = ?"))	
		{
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setInt(3, patron.getPatId());
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Patron: " + fname + " " + lname + " successfully updated");
				return true;
			} else {
				System.out.println("Patron: " + patron.getfName() + " " + patron.getlName() + " failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Patron: " + patron.getfName() + " " + patron.getlName() + " failed to update");
			return false;
		}
	}
	
	//WORKING
	public boolean updateUsername(Patron patron, String username) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "UPDATE patron SET username = ?"
				+ " WHERE patron_id = ?"))	
		{
			pstmt.setString(1, username);
			pstmt.setInt(2, patron.getPatId());
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Patron username successfully updated");
				return true;
			} else {
				System.out.println("Patron username failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Patron username failed to update");
			return false;
		}
	}
	
	//WORKING
	public boolean updatePassword(Patron patron, String password) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "UPDATE patron SET password = ?"
				+ " WHERE patron_id = ?"))	
		{
			pstmt.setString(1, password);
			pstmt.setInt(2, patron.getPatId());
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Patron password successfully updated");
				return true;
			} else {
				System.out.println("Patron password failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Patron password failed to update");
			return false;
		}
		
	}
	
	//delete operations
	//WORKING
	public boolean deletePatron(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "DELETE FROM patron WHERE patron_id = ?"))	
		{
			pstmt.setInt(1, id);
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Patron successfully deleted");
				return true;
			} else {
				System.out.println("Patron failed to delete");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Patron failed to delete");
			return false;
		}
	}

}
