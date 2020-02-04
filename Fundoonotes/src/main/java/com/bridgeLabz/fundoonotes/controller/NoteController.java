package com.bridgeLabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.fundoonotes.dto.NoteDto;
import com.bridgeLabz.fundoonotes.model.NoteModel;
import com.bridgeLabz.fundoonotes.response.Response;
import com.bridgeLabz.fundoonotes.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController 
{
	@Autowired
	private NoteService noteservice;

	@PostMapping("/create")
	private ResponseEntity<Response> createNote(@RequestBody NoteDto notedto, @RequestParam String token)
			throws Exception
	{

		NoteModel note = noteservice.createNote(notedto, token);

		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Note is created successfully", note));

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteNote(@RequestParam("token") String token,
			@RequestParam("id") long id)
	{

		boolean result = noteservice.deleteForever(token, id);
		if (result)
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("succussfully deleted", 200));
		} 
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("Something went wrong can't delete", 400));
		}
	}
	
	@DeleteMapping("/deleteAllNotes")
	public ResponseEntity<Response> deleteAllNotes(@RequestParam("token") String token)
	{
		boolean result = noteservice.emptybin(token);
		if(result)
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("successfully deleted", 200));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response("Something went wrong can't delete", 400));
	}

	@PutMapping("/updatenote")
	public ResponseEntity<Response> updateNote(@RequestBody NoteDto notedto, @RequestParam("token") String token,
			@RequestParam("id") long id) 
	{
		boolean result = noteservice.updateNote(notedto, token, id);
		if (result) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("Note is update successfully", 200));
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong", 400));
		}
	}

	@PostMapping("/pinunpin")
	public ResponseEntity<Response> pin(@RequestParam("token") String token, @RequestParam("id") long id) 
	{
		int result = noteservice.pin(token, id);
		if (result == 1) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("succussfully unPinned", 200));
		} 
		else if (result == 0) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("succussfully Pinned", 200));
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong", 400));
		}
	}

	@PostMapping("/archive")
	private ResponseEntity<Response> archive(@RequestParam("id") long id, @RequestParam("token") String token) {

		int result = noteservice.archive(token, id);
		if (result == 1) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("succussfully removed from Archive", 200));
		} 
		else if (result == 0) 
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response("succussfully Archived", 200));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Something went wrong", 400));
		}
	}
	
	@GetMapping("/allnotes")
	public ResponseEntity<Response> getAllNotes(@RequestParam("token") String token) 
	{
		List<NoteModel> notesList = noteservice.getAllNotes(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "all notes of user", notesList));
	}
	
	@PostMapping("/addcolor")
	public ResponseEntity<Response> addColor(@RequestParam("token") String token, @RequestParam("id") long id,@RequestParam("color") String color)
	{
		boolean result = noteservice.addcolor(token, id, color);
		if(result)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("color is added", 200));
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Something went wrong...", 400));
	}
	
	@PostMapping("/reminder")
	public ResponseEntity<Response> reminder(@RequestParam("token") String token, @RequestParam("id") long id)
	{
		boolean result = noteservice.reminder(token, id);
		if(result)
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200,"reminder added", result));
		return ResponseEntity.status(HttpStatus.OK).body(new Response("something went wrong", 400));
	}
	
	public ResponseEntity<Response> collaborator()
	{
		
		return null;
	}
	
	public ResponseEntity<Response> sort()
	{
			return null;
	}

}
