package com.bridgeLabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.fundoonotes.model.CollaboratorModel;
import com.bridgeLabz.fundoonotes.response.Response;
import com.bridgeLabz.fundoonotes.service.CollaboratorService;

@RestController
public class CollaboratorController 
{

	@Autowired
	private CollaboratorService collaboratorServ;

	@PostMapping("/AddCollaborator/")
	public ResponseEntity<Response> AddCollaborator(@RequestParam String collaboratorMailId, @RequestParam Long noteId) 
	{
		CollaboratorModel result = collaboratorServ.saveData(collaboratorMailId, noteId);
		if (result != null)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added collaborator", 200));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("already exists!!", 400));
	}

	@DeleteMapping("/deleteCollaborator/")
	public ResponseEntity<Response> deleteCollaborator(@RequestParam String collaboratorMailId, @RequestParam Long noteId) 
	{
		boolean result = collaboratorServ.deleteData(collaboratorMailId, noteId);
		if (result) 
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted collaborator..!", 200));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("something went wrong!!", 400));
	}

	@GetMapping("/AllCollaborators/")
	public ResponseEntity<Response> AllCollaborators(@RequestParam Long noteId) 
	{
		List<CollaboratorModel> result = collaboratorServ.getListOfCollaberators(noteId);
		if (result != null) 
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response(200,"List Of all collaborators",result));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("something went wrong!!", 400));
	}
}