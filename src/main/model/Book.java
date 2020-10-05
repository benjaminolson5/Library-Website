package com.cognixia.jump.model;

public class Book {

	private String isbn;
	private String title;
	private String descr;
	private boolean rented;
	private String dateAdded;
	
	public Book(String isbn, String title, String descr, boolean rented, String dateAdded) {
		this.isbn = isbn;
		this.title = title;
		this.descr = descr;
		this.rented = rented;
		this.dateAdded = dateAdded;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", descr=" + descr + ", rented=" + rented + ", dateAdded="
				+ dateAdded + "]";
	}
	
	
	
	
}
