package com.bridgeLabz.fundoonotes.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.fundoonotes.model.CollaboratorModel;
import com.bridgeLabz.fundoonotes.model.NoteModel;
import com.bridgeLabz.fundoonotes.repository.CollaboratorRepository;
import com.bridgeLabz.fundoonotes.repository.NoteRepository;
import com.bridgeLabz.fundoonotes.service.CollaboratorService;

@Service
public class CollaboratorServiceImp implements CollaboratorService
{

	@Autowired
	private NoteRepository noteRepos;
	
	@Autowired
	private CollaboratorRepository collaboRepo;
	
	@Override
	public CollaboratorModel saveData(String collaboratorMail,Long noteId) 
	{
     NoteModel notes = noteRepos.findByNoteId(noteId);
     if(notes!=null) 
     {
    	 CollaboratorModel collaborator = collaboRepo.findByMail(collaboratorMail, noteId);
    	 if(collaborator==null)
    	 {
    		 System.out.println("id="+noteId);
    		 CollaboratorModel collaboratorModel = new CollaboratorModel();
    		 collaboratorModel.setCollaborator(collaboratorMail);
    		 collaboratorModel.setNotes(notes);
    		collaboRepo.insertData(collaboratorModel.getCollaborator(), noteId);
    		return collaboratorModel;
    	 }
     }
		return null;
	}

	@Override
	public boolean deleteData(String collaboratorMail, Long noteId)
	{
      CollaboratorModel collaborator = collaboRepo.findByCmail(collaboratorMail, noteId);
      if(collaborator!=null)
      {
    	  System.out.println("email="+collaboratorMail);
    	  Long cId = collaborator.getcId();
    	  collaboRepo.deleteData(cId);
    	  return true;
      }
		return false;
	}

	@Override
	public List<CollaboratorModel> getListOfCollaberators(Long noteId) 
	{
       NoteModel notes = noteRepos.findByNoteId(noteId);
       if(notes!=null)
       {
    	   return collaboRepo.getAllCollaborators(noteId);
       }
		return null;
	}

	
}
