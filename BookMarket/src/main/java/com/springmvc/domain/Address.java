package com.springmvc.domain;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

	private static final long serialVersionUID = -824607634067469239L;
	
	private String detailName; // 세부 주소
	private String addressName; // 주소
	private String country; // 국가명
	private String zipCode; // 우편번호
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(addressName, country, detailName, zipCode);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(addressName, other.addressName) && Objects.equals(country, other.country)
				&& Objects.equals(detailName, other.detailName) && Objects.equals(zipCode, other.zipCode);
	}

}
