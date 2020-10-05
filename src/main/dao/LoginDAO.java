package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.connection.ConnectionManager;

public class LoginDAO {

	private static final Connection conn = ConnectionManager.getConnection();
	
	public int login(String username, String password, boolean isLibrarian) {
		int id = -1;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//select from proper table
			if(isLibrarian) {
				stmt= conn.prepareStatement("select * from librarian where username = ? and password = ?");
			}
			else {
				stmt= conn.prepareStatement("select * from patron where username = ? and password = ?");
			}
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			//if username/password matched a record, rs will have a value and return true on rs.next
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	
}
