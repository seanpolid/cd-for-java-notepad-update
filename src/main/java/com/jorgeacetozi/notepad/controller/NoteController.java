package com.jorgeacetozi.notepad.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jorgeacetozi.notepad.model.Note;
import com.jorgeacetozi.notepad.service.INoteService;

import jakarta.validation.Valid;

@Controller
public class NoteController {
	
	private final INoteService noteService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public NoteController(INoteService noteService) {
		this.noteService = noteService;
	}
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		//List<Note> notes = noteService.findAll();
		List<Note> notes = new ArrayList<>();
		notes.add(new Note("Title", "Description"));
		modelAndView.addObject("notes", notes);
		return modelAndView;
	}
	
	@PostMapping("/notes")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Note create(@RequestBody @Valid Note note) {
		logger.debug("Created note request: {}", note);
		return noteService.create(note);
	}
}
