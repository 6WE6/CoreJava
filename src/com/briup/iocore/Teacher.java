package com.briup.iocore;

import java.io.Serializable;

public class Teacher implements Serializable{
	private String name;
	private ObjectSequenceBean student;
	public Teacher(String name,ObjectSequenceBean student){
		this.name = name;
		this.student = student;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ObjectSequenceBean getStudent() {
		return student;
	}
	public void setStudent(ObjectSequenceBean student) {
		this.student = student;
	}
	
	
	

}
