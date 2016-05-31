package com.edu.karolina.beware.model;

public class UserVO {

	private String name;
	private String lastname;
	private String nickname;
	private String password;
	private int age;
	private String gender;
	private int height;
	private int weight;

	public UserVO() {
		super();
	}

	public UserVO(String name, String lastname, String nickname,
			String password, int age, String gender, int height, int weight) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.nickname = nickname;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
