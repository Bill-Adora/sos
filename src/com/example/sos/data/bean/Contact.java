package com.example.sos.data.bean;

public class Contact {
	private String id;
	private String fullName;
	private String phone;
	private String email;
	private String relation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Contact() {
	}

	public Contact(String id, String fullName, String phone, String email,
			String relation) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.relation = relation;

	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
