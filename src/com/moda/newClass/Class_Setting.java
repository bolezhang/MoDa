package com.moda.newClass;

public class Class_Setting {

	int index;
	String textabove;
	String textbelow;

	public Class_Setting(int id, String textabove, String textbelow) {
		this.index = id;
		this.textabove = textabove;
		this.textbelow = textbelow;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTextabove() {
		return textabove;
	}

	public void setTextabove(String textabove) {
		this.textabove = textabove;
	}

	public String getTextbelow() {
		return textbelow;
	}

	public void setTextbelow(String textbelow) {
		this.textbelow = textbelow;
	}

}
