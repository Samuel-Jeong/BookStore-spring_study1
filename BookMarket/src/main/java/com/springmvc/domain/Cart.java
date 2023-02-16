package com.springmvc.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cart implements Serializable {

	private static final long serialVersionUID = -8285727197850448534L;
	
	private String cartId; // 장바구니 ID
	private Map<String, CartItem> cartItems; // 장바구니 항목
	private int grandTotal; // 총액
	
	public Cart() {
		cartItems = new HashMap<>();
		grandTotal = 0;
	}
	
	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void updateGrandTotal() {
		this.grandTotal = 0;
		for (CartItem cartItem : cartItems.values()) {
			grandTotal += cartItem.getTotalPrice();
		}
	}
	
	public void addCartItem(CartItem cartItem) {
		// 현재 등록하기 위한 도서 ID 가져오기
		String bookId = cartItem.getBook().getBookId();
		
		if (cartItems.containsKey(bookId)) {
			CartItem curCartItem = cartItems.get(bookId);
			curCartItem.setQuantity(curCartItem.getQuantity() + cartItem.getQuantity());
			cartItems.put(bookId, curCartItem);
		} else {
			cartItems.put(bookId, cartItem);
		}
		
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem cartItem) {
		String bookId = cartItem.getBook().getBookId();
		cartItems.remove(bookId);
		updateGrandTotal();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, cartItems, grandTotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(cartItems, other.cartItems)
				&& grandTotal == other.grandTotal;
	}

}
