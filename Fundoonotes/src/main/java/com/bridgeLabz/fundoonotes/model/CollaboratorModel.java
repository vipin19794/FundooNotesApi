package com.bridgeLabz.fundoonotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Collaborator")
public class CollaboratorModel
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collaborator_id")
	private Long cId;

	@Column(name = "collaborator_mail")
	private String collaborator;

	@ManyToOne
	@JoinColumn(name = "note_id")
	private NoteModel notes;

	public Long getcId() 
	{
		return cId;
	}

	public void setcId(Long cId)
	{
		this.cId = cId;
	}

	public String getCollaborator()
	{
		return collaborator;
	}

	public void setCollaborator(String collaborator)
	{
		this.collaborator = collaborator;
	}

	public NoteModel getNotes()
	{
		return notes;
	}

	public void setNotes(NoteModel notes) 
	{
		this.notes = notes;
	}
	
	
}
