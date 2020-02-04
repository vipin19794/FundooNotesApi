package com.bridgeLabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgeLabz.fundoonotes.model.ProfilePicModel;
import com.bridgeLabz.fundoonotes.response.Response;
import com.bridgeLabz.fundoonotes.service.ProfilePicService;

@RestController
public class ProfilePicController 
{

	@Autowired
	private ProfilePicService profileService;

	@PostMapping("/upload-ProfilePic/{token}")
	public ResponseEntity<Response> uploadPic(@ModelAttribute MultipartFile multipartFile,
			@RequestParam String fileName, @PathVariable("token") String token) 
	{
		ProfilePicModel result = profileService.uploadProfilePic(multipartFile, fileName, token);
		if (result != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("successfully uploaded", 200));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("profile pic not uploaded", 400));
	}

	@DeleteMapping("/delete-profilePic")
	public ResponseEntity<Response> deletePicture(@RequestParam Long profileId)
	{
		boolean result = profileService.deleteFileName(profileId);
		if (result)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("successfully profile pic deleted", 200));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("something went wrong while deleting..", 200));
	}
}
