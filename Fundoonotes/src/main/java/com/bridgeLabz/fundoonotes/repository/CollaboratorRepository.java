package com.bridgeLabz.fundoonotes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgeLabz.fundoonotes.model.CollaboratorModel;

@Repository
@Transactional
public interface CollaboratorRepository extends JpaRepository<CollaboratorModel, Long>
{

	@Query(value="select * from collaborator where collaborator_mail=? AND note_id=?",nativeQuery = true)
	CollaboratorModel findByMail(String collaborator_mail,Long note_id);
	
	@Query(value = "select * from collaborator where collaborator_mail=? AND note_id=?",nativeQuery = true)
	CollaboratorModel findByCmail(String collaborator_mail, Long noteId);
	
	@Modifying
	@Query(value = "insert into collaborator(collaborator_mail,note_id) values(?,?)",nativeQuery = true)
	void insertData(String collaborator_mail,Long note_id);
	
	@Modifying
	@Query(value="delete from collaborator where collaborator_id=?",nativeQuery = true)
	void deleteData(Long collaborator_id);
	
	@Query(value="select * from collaborator where note_id=?",nativeQuery = true)
	List<CollaboratorModel> getAllCollaborators(Long noteId);
}
