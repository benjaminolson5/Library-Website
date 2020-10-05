package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.time.LocalDate;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Patron;

	public class BookCheckoutDAO {
		
		private static Connection conn = ConnectionManager.getConnection();
		private static final String ADD_CHECKOUT ="INSERT INTO book_checkout(patron_id, isbn, checkedout, due_date) "
																				+ "VALUES (?, ?, CONVERT(?, DATE), CONVERT(?, DATE))";
		private static final String RETURN_CHECKOUT = "UPDATE book_checkout SET returned=CONVERT(?, DATE) WHERE checkout_id=?";
		
		private static BookDAO myBookDAO = new BookDAO();
//		private static BookCheckoutDAO myBCDAO = new BookCheckoutDAO();
		
		public boolean addCheckoutLog(Book book, Patron patron) {
			boolean success = false;
			if (!patron.isAccFrozen()) {
			LocalDate checkoutDate= LocalDate.now();
			LocalDate dueDate= checkoutDate.plusDays(7);
				
			BookCheckout log = new BookCheckout(
										patron.getPatId(),
										book.getIsbn(),
										checkoutDate.toString(),
										dueDate.toString());
				
			try (PreparedStatement pstmt = conn.prepareStatement(ADD_CHECKOUT, Statement.RETURN_GENERATED_KEYS)) {
				pstmt.setInt(1, log.getPatId());
				pstmt.setString(2, log.getIsbn());
				pstmt.setString(3,  log.getCheckedOut());
				pstmt.setString(4,  log.getDueDate());
				
				System.out.println(pstmt);
				
				
				int added = pstmt.executeUpdate();
				System.out.println(pstmt);
				if (added > 0) {
					try (ResultSet generatedKeys = pstmt.getGeneratedKeys()){ 
						if (generatedKeys.next()) {
							log.setCheckId(generatedKeys.getInt(1));
						}
					}
					myBookDAO.bookRented(myBookDAO.getBookByISBN(log.getIsbn()), true);
					System.out.println("Book: " + myBookDAO.getBookByISBN(log.getIsbn()) + " successfully checked out.");
					System.out.println(log.toString());
					success = true;
				} else {
					System.out.println("Failed to checkout book: " + myBookDAO.getBookByISBN(log.getIsbn()));
				}
				
			} catch (SQLException e) {
				System.out.println("Failed to checkout book: " + myBookDAO.getBookByISBN(log.getIsbn()));	
				}
			} 
			else {
				
				System.out.println("Your account is frozen, you cannot check out a book.");
			}
			return success;
		}

		
		//read
		public static BookCheckout getCheckoutLogById(int theCheckoutId) {
			try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM book_checkout WHERE checkout_id = ?")) {
				pstmt.setInt(1, theCheckoutId);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
				BookCheckout byId = new BookCheckout(
						rs.getInt(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						);
				
				byId.setCheckId(rs.getInt(1));
				
				rs.close();
				return byId;
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		
		//IN PROGRESS
		public static boolean returnCheckout(int theCheckoutId) {
			
			boolean status = false;
			String returnDate = LocalDate.now().toString();
			BookCheckout log = BookCheckoutDAO.getCheckoutLogById(theCheckoutId);

			
			try (PreparedStatement pstmt = conn.prepareStatement(RETURN_CHECKOUT))	
			{
				
				pstmt.setString(1, returnDate);
				pstmt.setInt(2, theCheckoutId);
				int added = pstmt.executeUpdate(); 
				

				if (added > 0) {

					myBookDAO.bookRented(myBookDAO.getBookByISBN(log.getIsbn()), false);
					System.out.println("Book: " + myBookDAO.getBookByISBN(log.getIsbn()) + " successfully returned.");
					System.out.println(log.toString());
					status = true;
				} else {
					System.out.println("Failed to return book: " + myBookDAO.getBookByISBN(log.getIsbn()));
				}
				return status;
				
			} catch (SQLException e) {
				System.out.println("Failed to return book: " + myBookDAO.getBookByISBN(log.getIsbn()));	
				return status;
			}

			}
}
