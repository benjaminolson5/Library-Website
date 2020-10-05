package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Librarian;

public class LibrarianDAO {
	
	private Connection conn = ConnectionManager.getConnection();

	//create operations
	//WORKING
	public boolean addLibrarian(Librarian librarian) {
		try (Statement stmt = conn.createStatement()) {
			int added = stmt.executeUpdate("INSERT INTO librarian(username, password)"
					+ "				VALUES('" + librarian.getUser() + "', '" + librarian.getPass() + "')");

					if (added > 0) {
						System.out.println("New librarian added to the library");
						return true;
					} else {
						System.out.println("Failed to add new librarian to the library");
						return false;
					}
		} catch (SQLException e) {
			System.out.println("Failed to add new librarian to the library");
			return false;
		}
	}
	
	//WORKING
	public boolean addBook(Book book) {
			BookDAO.createNewBook(book);
			return true;
	}
	
	//read operations
	//WORKING
	public Librarian getLibrarianById(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM librarian WHERE librarian_id = ?")) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			
			
			Librarian byId = new Librarian(
					rs.getString(2), 
					rs.getString(3)
					);
			
			byId.setLibId(rs.getInt(1));
			
			rs.close();
			return byId;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//update operations
	//WORKING
	public boolean updateUsername(Librarian librarian, String username) {
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE librarian SET username = ?"
				+ " WHERE librarian_id = ?"))	
		{
			pstmt.setString(1, username);
			pstmt.setInt(2, librarian.getLibId());
			
			int added = pstmt.executeUpdate(); 
			System.out.println(added);

			if (added > 0) {
				System.out.println("Librarian username successfully updated");
				return true;
			} else {
				System.out.println("HERE: Librarian username failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("THERE: Librarian username failed to update");
			return false;
		}
	}
	
	//WORKING
	public boolean updatePassword(Librarian librarian, String password) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "UPDATE librarian SET password = ?"
				+ " WHERE librarian_id = ?"))	
		{
			pstmt.setString(1, password);
			pstmt.setInt(2, librarian.getLibId());
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Librarian password successfully updated");
				return true;
			} else {
				System.out.println("Librarian password failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Librarian password failed to update");
			return false;
		}
	}
	
	//WORKING
	public boolean editTitle(Book book, String title) {
				BookDAO.updateTitle(book, title);
				return true;
	}

	//WORKING
	public boolean editDescription(Book book, String description) {
				BookDAO.updateDescription(book, description);
				return true;
	}
	
	//WORKING
	public boolean approvePatron(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "UPDATE patron SET account_frozen = false"
				+ " WHERE patron_id = ?"))	
		{
			//need it to not be based on model id
			pstmt.setInt(1, id);
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Patron account unfrozen");
				return true;
			} else {
				System.out.println("Failed to unfreeze patron account");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Failed to unfreeze patron account");
			return false;
		}
	}
	
	//delete operations
	//WORKING
	public boolean deleteLibrarian(int id) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "DELETE FROM librarian WHERE librarian_id = ?"))	
		{
			pstmt.setInt(1, id);
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Librarian successfully deleted");
				return true;
			} else {
				System.out.println("Librarian failed to delete");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Librarian failed to delete");
			return false;
		}
	}
}
