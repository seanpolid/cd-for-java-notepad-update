package com.jorgeacetozi.notepad.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jorgeacetozi.notepad.model.Note;
import com.jorgeacetozi.notepad.repository.INoteRepository;

@Service
public class NoteService implements INoteService {

	private final INoteRepository noteRepository;
	
	public NoteService(INoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	@Override
	public Note create(Note note) {
		return noteRepository.save(note);
	}

	@Override
	public void delete(Note note) {
		noteRepository.delete(note);
	}

	@Override
	public List<Note> findAll() {
		return noteRepository.findAll();
	}

}
