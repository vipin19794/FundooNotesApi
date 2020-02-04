package com.bridgeLabz.fundoonotes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgeLabz.fundoonotes.model.ProfilePicModel;

@Repository
@Transactional
public interface ProfilePicRepository extends JpaRepository<ProfilePicModel, Long> 
{
	@Modifying
	@Query(value = "insert into profilepicture(profile,user_id)values(?,?)", nativeQuery = true)
	void saveData(String fileName, Long id);

	@Query(value = "select * from profilepicture where id=?", nativeQuery = true)
	ProfilePicModel searchById(Long profileId);

	@Modifying
	@Query(value = "delete from profilepicture where id=?", nativeQuery = true)
	void deleteData(Long profileId);

	@Query(value = "select max(id) from profilepicture where user_id=:id", nativeQuery = true)
	Long getPicByUser(Long id);

}
