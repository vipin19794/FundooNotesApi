package com.bridgeLabz.fundoonotes.serviceImplement;

import java.io.File;
import java.io.FileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgeLabz.fundoonotes.model.ProfilePicModel;
import com.bridgeLabz.fundoonotes.model.UserModel;
import com.bridgeLabz.fundoonotes.repository.ProfilePicRepository;
import com.bridgeLabz.fundoonotes.repository.UserRepository;
import com.bridgeLabz.fundoonotes.service.ProfilePicService;
import com.bridgeLabz.fundoonotes.utility.Jwt;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProfilePicServiceImp implements ProfilePicService 
{

	
	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.bucket.name}")
	private String bucketName;

	@Autowired
	private Jwt jwt;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProfilePicRepository profileRepo;

	@Override
	public ProfilePicModel uploadProfilePic(MultipartFile multiFile, String fileName, String token) {
		String getfile = multiFile.getOriginalFilename();
		try
		{
			
			long id = jwt.parseJwtToken(token);
			UserModel user = userRepo.findById(id);
			if (user != null) 
			{
				// creating the file in the server temporarily
				File file = new File(fileName);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(multiFile.getBytes());
				fos.close();
	
				ProfilePicModel picture = new ProfilePicModel();
				picture.setProfileName(getfile);
				picture.setUser(user);
		
				profileRepo.saveData(picture.getProfileName(), id);
				Long pid = profileRepo.getPicByUser(id);
				PutObjectRequest objectRequest = new PutObjectRequest(bucketName, pid + getfile, file);
				amazonS3.putObject(objectRequest);
				
				// delete the file from the server
				file.delete();
				return picture;
			}
		} 
		catch (Exception e) 
		{
		System.out.println(e);
		
		}
		return null;
	}

	@Override
	public boolean deleteFileName(Long profileId) 
	{
		try
		{
			ProfilePicModel profile = profileRepo.searchById(profileId);
			if (profile != null) 
			{
				String fileName = profile.getProfileName();
				amazonS3.deleteObject(new DeleteObjectRequest(bucketName, profileId+fileName));
				profileRepo.deleteData(profileId);
				return true;
			}
		} 
		catch (Exception e) 
		{
			//log.error("error [" + e.getMessage() + "] occured while removing file");
			e.printStackTrace();
		}
		return false;
	}

}
