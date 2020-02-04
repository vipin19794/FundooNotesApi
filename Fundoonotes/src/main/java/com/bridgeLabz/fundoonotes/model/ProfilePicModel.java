package com.bridgeLabz.fundoonotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profilepicture")
public class ProfilePicModel 
{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;

	@Column(name = "profile")
	private String profileName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;

	public Long getProfileId()
	{
		return profileId;
	}

	public void setProfileId(Long profileId) 
	{
		this.profileId = profileId;
	}

	public String getProfileName() 
	{
		return profileName;
	}

	public void setProfileName(String profileName) 
	{
		this.profileName = profileName;
	}

	public UserModel getUser()
	{
		return user;
	}

	public void setUser(UserModel user) 
	{
		this.user = user;
	}
	
	
}
