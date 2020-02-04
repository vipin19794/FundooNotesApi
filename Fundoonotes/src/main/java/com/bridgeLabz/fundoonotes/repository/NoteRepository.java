package com.bridgeLabz.fundoonotes.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgeLabz.fundoonotes.model.NoteModel;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<NoteModel, Long> {

	@Query(value = "select * from note where id = :id", nativeQuery = true)
	NoteModel findById(long id);
	
	@Query(value = "select * from note where id=?", nativeQuery = true)
	NoteModel findByNoteId(Long note_id);
	
	@Query(value = "select * from note where user_id = :userid", nativeQuery = true)
	NoteModel findByuserid(long userid);

	@Modifying
	@Query(value = "insert into note (content, created_at,  title, updated_at, user_id) values ( :content, :createdAt, :title, :updatedAt, :id)" , nativeQuery = true)
	public void insertData(String content, Date createdAt, String title, Date updatedAt, long id);

	@Modifying
	@Query(value = "update note set is_pinned = :b where user_id = :userid AND id = :id", nativeQuery = true)
	void setPinned(boolean b, long userid, long id);

	@Modifying
	@Query(value = "update note set is_archived = :b where user_id = :userid AND id = :id", nativeQuery = true)
	void setArchive(boolean b, long userid, long id);

	@Query(value = "select * from note where user_id = :userId", nativeQuery = true)
	List<NoteModel> getAll(Long userId);
	
	@Modifying
	@Query(value = "delete from note  where user_id = :userId AND id = :id", nativeQuery = true)
	void deleteForever(long userId, long id);
	
	@Modifying
	//@Query(value = "delete from note where user_id = :userId and is_deleted = true", nativeQuery = true)
	@Query(value = "delete from note where user_id= :userId", nativeQuery = true)
	void empty(long userId);

	@Modifying
	@Query(value = "update note set content = :content , title = :title , updated_at = :updatedAt where user_id = :id AND id = :id2", nativeQuery = true)
	void updateData(String content, String title, Date updatedAt, long id, long id2);	

	@Modifying
	@Query(value = "update note set note_color = :color where user_id = :userid and id = :id", nativeQuery = true)
	void updateColor(long userid, long id, String color);

//	@Query(name = "tofindlabelfornote", nativeQuery = true)
//	List<LabelModel> getLabelByNoteId(long id);

}
