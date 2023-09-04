package com.jorgeacetozi.notepad.integrationTests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jorgeacetozi.notepad.model.Note;
import com.jorgeacetozi.notepad.service.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NoteServiceTest {

	@Autowired
	private NoteService noteService;
	private Note note;
	
	@BeforeEach
	public void setup() {
		note = new Note("Test Title", "Test Content");
	}
	
	@AfterEach
	public void destroy() {
		noteService.delete(note);
	}
	
	@Test
	public void shouldCreateNoteWithTitleAndContent() {
		Note createdNote = noteService.create(note);
		assertNotNull(createdNote.getId());
		assertEquals(2, createdNote.getWordCount());
	}
}
