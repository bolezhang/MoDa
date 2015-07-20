package com.moda.newClass;

public class Class_Language {

	int index;
	String chinese;
	String english;

	public Class_Language(int id, String chinese, String english) {
		this.index = id;
		this.chinese = chinese;
		this.english = english;
	}

	int getId() {
		return index;
	}

	public String getChinese() {
		return chinese;
	}

	public String getEnglish() {
		return english;
	}

	void setChinese(String chinese) {
		this.chinese = chinese;
	}

	void setEnglish(String english) {
		this.english = english;
	}
}
