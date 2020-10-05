package com.cognixia.jump.model;

public class Patron {

	//id auto-increments in mysql
	private int patId;
	private String fName;
	private String lName;
	private String user;
	private String pass;
	private boolean accFrozen;
	
	public Patron(String fName, String lName, String user, String pass, boolean accFrozen) {
		this.fName = fName;
		this.lName = lName;
		this.user = user;
		this.pass = pass;
		this.accFrozen = accFrozen;
	}

	public int getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
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

	public boolean isAccFrozen() {
		return accFrozen;
	}

	public void setAccFrozen(boolean accFrozen) {
		this.accFrozen = accFrozen;
	}

	@Override
	public String toString() {
		return "Patron [patId=" + patId + ", fName=" + fName + ", lName=" + lName + ", user=" + user + ", pass=" + pass
				+ ", accFrozen=" + accFrozen + "]";
	}
	
	
	
}
