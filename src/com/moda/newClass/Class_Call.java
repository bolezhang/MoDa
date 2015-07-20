package com.moda.newClass;

public class Class_Call {

	String name;
	String phoneNumber;
	Object imagePath;

	public Class_Call(Object imagePath, String name, String phoneNumber) {
		this.imagePath = imagePath;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Object getImagePath() {
		return imagePath;
	}

	public void setImagePath(Object path) {
		this.imagePath = path;
	}
}
