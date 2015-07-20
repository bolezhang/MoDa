package com.moda.newClass;

public class Class_Website {

	int index;
	String name;
	String address;
	Object imagePath;

	public Class_Website(int id, Object imagePath, String name, String address) {
		this.index = id;
		this.imagePath = imagePath;
		this.name = name;
		this.address = address;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Object getImagePath() {
		return imagePath;
	}

	public void setImagePath(Object path) {
		this.imagePath = path;
	}
}
