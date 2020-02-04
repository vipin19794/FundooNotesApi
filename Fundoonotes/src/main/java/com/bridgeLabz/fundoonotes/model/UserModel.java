package com.bridgeLabz.fundoonotes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class UserModel
{
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
       private long     id;
	   
	   @NotNull
       private String  fname;
	   
	   @NotNull
       private String  lname;
       
       @NotNull
       @Column(unique = true)
       private String  email;
       
       @NotNull
       private String  mobile;
       
       @NotNull
       private String  city;
       
       @NotNull
       private String  state;
       
       @NotNull
       private String  country;
       
       @NotNull
       private String  pincode;
       
       @NotNull
       @Column(unique = true)
       private String  username;
       
       @NotNull
       private String  password;
       
       @NotNull
       @Column(columnDefinition = "boolean default false")
       private boolean isVerified;
       
       private Date    creatorStamp;
       
       public UserModel()
       {
    	   
       }
       
	   public UserModel(long id, @NotNull String fname, @NotNull String lname, @NotNull String email, @NotNull String mobile, @NotNull String city, @NotNull String state,
			   @NotNull String country, @NotNull String pincode, @NotNull String username, @NotNull String password, @NotNull boolean isVerified, Date creatorStamp) 
	   {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.username = username;
		this.password = password;
		this.isVerified = isVerified;
		this.creatorStamp = creatorStamp;
	}

	public UserModel(String fname, String lname, String email, String mobile, String city, String state, 
			         String country, String pincode, String username, String password) 
	{
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Date getCreatorStamp() {
		return creatorStamp;
	}

	public void setCreatorStamp(Date creatorStamp) {
		this.creatorStamp = creatorStamp;
	}
       
	 
	   
       
       
       
       
       
}
