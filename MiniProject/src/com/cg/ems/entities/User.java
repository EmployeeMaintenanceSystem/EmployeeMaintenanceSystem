package com.cg.ems.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="User_Master")
public class User{

	@Id
	@NotEmpty(message="UserId cannot be left blank")
	@Column(name="User_Id")
	private String userId;
	
	
	@Column(name="User_Name")
	private String userName;
	
	@NotEmpty(message="Please enter a password")
	@Column(name="User_Password")
	private String password;
	
	@Column(name="User_Type")
	private String userType;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
