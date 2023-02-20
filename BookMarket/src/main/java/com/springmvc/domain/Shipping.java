package com.springmvc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

public class Shipping implements Serializable {

	private static final long serialVersionUID = 1493936227962677468L;

	private String name; // 배송 고객 이름

	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date; // 배송일

	private Address address; // 배송 주소 객체

	public Shipping() {
		this.address = new Address();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, date, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Shipping other = (Shipping) obj;
		return Objects.equals(address, other.address) && Objects.equals(date, other.date)
				&& Objects.equals(name, other.name);
	}

}
