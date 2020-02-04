package com.bridgeLabz.fundoonotes.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.fundoonotes.dto.NoteDto;
import com.bridgeLabz.fundoonotes.model.NoteModel;
import com.bridgeLabz.fundoonotes.model.UserModel;
import com.bridgeLabz.fundoonotes.repository.NoteRepository;
import com.bridgeLabz.fundoonotes.repository.UserRepository;
import com.bridgeLabz.fundoonotes.service.NoteService;
import com.bridgeLabz.fundoonotes.utility.Jwt;

@Service
public class NoteServiceImp implements NoteService {

	@Autowired
	private Jwt tokenGenerator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Override
	public NoteModel createNote(NoteDto noteDto, String token) {
		long id = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findById(id);
		if (user != null) {
			NoteModel note = new NoteModel(noteDto.getTitle(), noteDto.getContent());
			note.setCreatedBy(user);
			note.setCreatedAt();
			noteRepository.insertData(note.getContent(), note.getCreatedAt(), note.getTitle(), note.getUpdatedAt(),
					note.getCreatedBy().getId());
			return note;
		}
		return null;
	}

	@Override
	public boolean deleteForever(String token, long id)
	{
		long userId = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findById(userId);
       
		if (user != null)
		{		
				noteRepository.deleteForever(userId, id);
				return true;
		}
		return false;
	}

	@Override
	public boolean emptybin(String token) 
	{
		long userId = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findById(userId);
		if (user != null) 
		{
			noteRepository.empty(userId);
			return true;
		}

		return false;
	}

	@Override
	public boolean updateNote(NoteDto notedto, String token, long id) {
		long userid = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findById(userid);
		if (user != null) {
			NoteModel note = noteRepository.findById(id);
			note.setContent(notedto.getContent());
			note.setTitle(notedto.getTitle());
			note.setUpdatedAt();
			noteRepository.updateData(note.getContent(), note.getTitle(), note.getUpdatedAt(), id, id);
			return true;
		}
		return false;
	}

	@Override
	public int pin(String token, long id) 
	{
		long userid = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findById(userid);
		if (user != null)
		{
			NoteModel note = noteRepository.findById(id);
			if (note.isPinned())
			{
				noteRepository.setPinned(false, userid, id);
				return 1;
			}
			else if (!note.isPinned()) 
			{
				noteRepository.setArchive(false, userid, id);
				noteRepository.setPinned(true, userid, id);
				return 0;
			} else
			{
				return -1;
			}
		}
		return 0;
	}

	@Override
	public int archive(String token, long id) 
	{
		long userid = tokenGenerator.parseJwtToken(token);
		UserModel user = userRepository.findById(userid);
		if (user != null) 
		{
			NoteModel note = noteRepository.findById(id);
			if (note.isArchived()) 
			{
				noteRepository.setArchive(false, userid, id);
				return 1;
			} 
			else if (!note.isArchived()) 
			{
				noteRepository.setPinned(false, userid, id);
				noteRepository.setArchive(true, userid, id);
				return 0;
			}
			else
			{
				return -1;
			}
		}
		return -1;
	}

	@Override
	public List<NoteModel> getAllNotes(String token)
	{
		long userId = tokenGenerator.parseJwtToken(token);
		Object isUserAvailable = userRepository.findById(userId);
		if (isUserAvailable != null) {
			List<NoteModel> notes = noteRepository.getAll(userId);
			return notes;
		}
		return null;
	}

	@Override
	public boolean reminder(String token, long id) 
	{

		return true;
	}

	@Override
	public boolean addcolor(String token, long id, String color) {
		try {
			long userid = tokenGenerator.parseJwtToken(token);
			UserModel isUserAvailable = userRepository.findById(userid);
			if (isUserAvailable != null) {
				NoteModel note = noteRepository.findById(id);
				if (note != null) {
					noteRepository.updateColor(userid, id, color);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public List<LabelModel> allLabelofOneNote(String token, long id) {
//		long userId = tokenGenerator.parseJwtToken(token);
//		UserModel user = userRepository.findById(userId);
//		if(user != null)
//		{
//			NoteModel note = noteRepository.findById(id);
//			if(note != null)
//			{
//				List<LabelModel> label = noteRepository.getLabelByNoteId(id);
//				return label;
//			}
//		}
//		return null;
//	}

}
