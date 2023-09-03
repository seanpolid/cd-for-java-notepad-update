package com.jorgeacetozi.notepad.service;

import java.util.List;

import com.jorgeacetozi.notepad.model.Note;

public interface INoteService {

	Note create(Note note);
	void delete(Note note);
	List<Note> findAll();
	
}
