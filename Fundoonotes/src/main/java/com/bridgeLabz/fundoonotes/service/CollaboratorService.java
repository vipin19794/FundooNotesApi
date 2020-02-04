package com.bridgeLabz.fundoonotes.service;

import java.util.List;
import com.bridgeLabz.fundoonotes.model.CollaboratorModel;

public interface CollaboratorService 
{
	CollaboratorModel saveData(String collaboratorMail,Long noteId);
	
	boolean deleteData(String collaboratorMail, Long noteId);
	List<CollaboratorModel> getListOfCollaberators(Long noteId);
}
