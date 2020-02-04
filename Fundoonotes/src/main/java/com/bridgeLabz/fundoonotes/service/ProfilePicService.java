package com.bridgeLabz.fundoonotes.service;

import org.springframework.web.multipart.MultipartFile;

import com.bridgeLabz.fundoonotes.model.ProfilePicModel;

public interface ProfilePicService 
{

	ProfilePicModel uploadProfilePic(MultipartFile file,String fileName,String token);
	
	boolean deleteFileName(Long profileId);
}
