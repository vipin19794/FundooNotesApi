package com.bridgeLabz.fundoonotes.repository;


import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeLabz.fundoonotes.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Long> 
{	
	@Query(value="Select * from user_model where email = :email",nativeQuery = true)
	UserModel findEmail(String email);
	
	@Query(value = "select * from user_model where email=?", nativeQuery = true)
	UserModel findByEmail(String user_mail);
	
	@Query(value = "select * from user_model where id = :id", nativeQuery = true)
	UserModel findById(long id);

	@Modifying
	@Query(value="Insert into user_model(fname,lname,email,mobile,city,state,country,pincode,username,password,is_verified,creator_stamp) values (:fname,:lname,:email,:mobile,:city,:state,:country,:pincode,:username,:password,:isVerified,:creator_stamp)",nativeQuery = true)
	void insertdata(String fname, String lname, String email, String mobile, String city, String state, String country, String pincode, String username, String password ,boolean isVerified, Date creator_stamp);

	@Modifying
	@Query(value="update user_model set is_verified = true where id = :id", nativeQuery = true)
	void verify(long id);
}
