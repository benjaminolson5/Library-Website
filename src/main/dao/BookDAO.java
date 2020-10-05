package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;
public class BookDAO {

	private static Connection conn = ConnectionManager.getConnection();

	private static final String UPDATE_RENTED = "UPDATE book SET rented = ? WHERE isbn = ?";
	
	//create
	//WORKING
	public static boolean createNewBook(Book book) {
		try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO book(isbn, title, descr, added_to_library)"
				+ "					VALUES('" + book.getIsbn() + "', '" + book.getTitle() + "', '"
				+ book.getDescr() + "', CONVERT(?, DATE))")) {
			pstmt.setString(1, book.getDateAdded());
			int added = pstmt.executeUpdate();

			if (added > 0) {
				System.out.println("New book: " + book.getTitle() + " added to the library");
				return true;
			} else {
				System.out.println("Failed to add " + book.getTitle() + " to the library");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Failed to add " + book.getTitle() + " to the library");
			return false;
		}
	}
	
	//read
	//WORKING
	public Book getBookByISBN(String isbn) {
		
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM book WHERE isbn = ?")) {
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			
			Book byIsbn = new Book(rs.getString(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getBoolean(4),
					rs.getString(5)
					);
			
			rs.close();
			return byIsbn;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//WORKING
	public List<Book> getAllBooks(boolean isAvailable) {
		
		if (isAvailable) {
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from book where rented = false");  )
			{
			
				List<Book> bookListT = new ArrayList<>();
				
				while (rs.next()) {
					
					Book book = new Book(
							rs.getString(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getBoolean(4),
							rs.getString(5)
							);
					bookListT.add(book);
				}
				return bookListT;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from book");  )
			{
			
				List<Book> bookListF = new ArrayList<>();
				
				while (rs.next()) {
					
					Book book = new Book(
							rs.getString(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getBoolean(4),
							rs.getString(5)
							);
					bookListF.add(book);
				}
				return bookListF;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
			
	//update
	//WORKING
	public boolean bookRented(Book theBook, boolean isRented) {
		boolean success = false;
		String prompt = isRented ? "checked out" : "returned";
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_RENTED)) {
			pstmt.setBoolean(1, isRented);
			pstmt.setString(2, theBook.getIsbn());
			int added = pstmt.executeUpdate(); 
			
			theBook.setRented(isRented);			
		
			if (added > 0) {
				theBook.setRented(isRented);	
				success = true;
				System.out.println("The Book: " + theBook.getTitle() + " was successfully " + prompt);
			} else {
				System.out.println("The Book: " + theBook.getTitle() + " failed to be " + prompt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("The Book: " + theBook.getTitle() + " failed to be " + prompt);
			
		}
		return success;
	}


	
	//WORKING
	public static boolean updateTitle(Book book, String title) {
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE book SET title = ?"
				+ " WHERE isbn = ?"))	
		{
			pstmt.setString(1, title);
			pstmt.setString(2, book.getIsbn());
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Book title successfully updated");
				return true;
			} else {
				System.out.println("Book title failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Book title failed to update");
			return false;
		}
	}
	
	//WORKING
	public static boolean updateDescription(Book book, String description) {
		try (PreparedStatement pstmt = conn.prepareStatement("UPDATE book SET descr = ?"
				+ " WHERE isbn = ?"))	
		{
			pstmt.setString(1, description);
			pstmt.setString(2, book.getIsbn());
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Book description successfully updated");
				return true;
			} else {
				System.out.println("Book description failed to update");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Book description failed to update");
			return false;
		}
	}
	
	
	//delete
	//WORKING
	public boolean deleteBook(Book book) {
		try (PreparedStatement pstmt = conn.prepareStatement(""
				+ "DELETE FROM book WHERE isbn = ?"))	
		{
			pstmt.setString(1, book.getIsbn());
			
			int added = pstmt.executeUpdate(); 

			if (added > 0) {
				System.out.println("Book successfully deleted");
				return true;
			} else {
				System.out.println("Book failed to delete");
				return false;
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("Book failed to delete");
			return false;
		}
	}
}
