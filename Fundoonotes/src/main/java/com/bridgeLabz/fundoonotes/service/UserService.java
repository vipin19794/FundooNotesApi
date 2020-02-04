package com.bridgeLabz.fundoonotes.service;

import org.springframework.stereotype.Component;

import com.bridgeLabz.fundoonotes.dto.LoginDto;
import com.bridgeLabz.fundoonotes.dto.ResetPasswordDto;
import com.bridgeLabz.fundoonotes.dto.UserDto;
import com.bridgeLabz.fundoonotes.model.UserModel;

@Component
public interface UserService 
{
	
	UserModel register(UserDto userdto);

	UserModel login(LoginDto logindto);

	UserModel verify(String token);

	UserModel forgetPassword(String email);
	
	UserModel resetPassword(ResetPasswordDto resetpassword, String token);

}
