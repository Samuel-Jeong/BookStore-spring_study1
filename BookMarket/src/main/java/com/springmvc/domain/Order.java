package com.springmvc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

	private static final long serialVersionUID = 4221771602900107933L;
	
	private Long orderId; // 주문 ID
	private Cart cart; // 장바구니 객체
	private Customer customer; // 고객 객체
	private Shipping shipping; // 배송지 객체
	
	public Order() {
		this.customer = new Customer();
		this.shipping = new Shipping();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cart, customer, orderId, shipping);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(cart, other.cart) && Objects.equals(customer, other.customer)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(shipping, other.shipping);
	}

}
