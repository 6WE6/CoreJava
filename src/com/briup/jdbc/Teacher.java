package com.briup.jdbc;

import java.sql.Date;

public class Teacher {
	private Integer id;
	private String name;
	private Integer age;
	private Date birth;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age + ", birth=" + birth + "]";
	}
	//包含所有参数的构造器
	public Teacher(Integer id, String name, Integer age, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
	}
	//无参的构造器
	public Teacher() {
		super();
	}
	//无主键的构造器
	public Teacher(String name, Integer age, Date birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}
	
	
	
}
