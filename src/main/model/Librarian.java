package com.cognixia.jump.model;

public class Librarian {
	
	//auto increments
	private int libId;
	private String user;
	private String pass;
	
	public Librarian(String username, String password) {
		this.user = username;
		this.pass = password;
	}

	public int getLibId() {
		return libId;
	}
	
	public void setLibId(int id) {
		this.libId= id;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "Librarian [lidId= " + libId + " username=" + user + ", password=" + pass + "]";
	}

}
