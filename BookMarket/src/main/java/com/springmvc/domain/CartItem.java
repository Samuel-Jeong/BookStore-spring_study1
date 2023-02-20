package com.springmvc.domain;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {

	private static final long serialVersionUID = -2220274791589791282L;

	private Book book; // 도서
	private int quantity; // 도서 개수
	private int totalPrice; // 도서 가격

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(Book book) {
		this.book = book;
		this.quantity = 1;
		this.totalPrice = book.getUnitPrice();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void updateTotalPrice() {
		this.totalPrice = this.book.getUnitPrice() * this.quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, quantity, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(book, other.book) && quantity == other.quantity && totalPrice == other.totalPrice;
	}

}
