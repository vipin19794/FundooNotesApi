package com.bridgeLabz.fundoonotes.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgeLabz.fundoonotes.dto.NoteDto;
import com.bridgeLabz.fundoonotes.model.NoteModel;


@Component
public interface NoteService 
{

	public NoteModel createNote(NoteDto createdto, String token);

	public boolean deleteForever(String token, long id);

	public boolean updateNote(NoteDto notedto, String token, long id);

	public int pin(String token, long id);

	public int archive(String token, long id);

	public List<NoteModel> getAllNotes(String token);

	public boolean reminder(String token, long id);

	public boolean emptybin(String token);

	public boolean addcolor(String token, long id, String color);

//	public List<LabelModel> allLabelofOneNote(String token, long id);

}
