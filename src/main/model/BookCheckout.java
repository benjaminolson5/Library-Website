package com.cognixia.jump.model;

public class BookCheckout {

	//auto increments
	private int checkId;
	private int patId;
	private String isbn;
	
	//MUST BE CONSTRUCTED WITH DATE PARAMETERS PROPERLY FORMATTED!
	private String checkedOut;
	private String dueDate;
	private String returned;
	
	public BookCheckout(int patId, String isbn, String checkedOut, String dueDate) {
		//id incremented based on static class attribute
		this.patId = patId;
		this.isbn = isbn;
		this.checkedOut = checkedOut;
		this.dueDate = dueDate;

	}
	
	public BookCheckout(int patId, String isbn, String checkedOut, String dueDate, String returned) {
		//id incremented based on static class attribute
		this.patId = patId;
		this.isbn = isbn;
		this.checkedOut = checkedOut;
		this.dueDate = dueDate;
		this.returned = returned;

	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public int getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(String checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}

	@Override
	public String toString() {
		return "BookCheckout [checkId=" + checkId + ", patId=" + patId + ", isbn=" + isbn + ", checkedOut=" + checkedOut
				+ ", dueDate=" + dueDate + ", returned=" + returned + "]";
	}
	
	
	
}
