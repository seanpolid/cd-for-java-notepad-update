package com.jorgeacetozi.notepad.unittests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jorgeacetozi.notepad.model.Note;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class NoteTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void shouldNotRaiseViolationWhenTitleAndContentAreFilled() {
		Note note = new Note("Unit Tests", "Unit tests provide fast feedback");
		Set<ConstraintViolation<Note>> constraintViolations = validator.validate(note);
		assertEquals(0, constraintViolations.size());
	}
	
	@Test
	public void shouldRaiseViolationWhenTitleIsEmpty() {
		Note note = new Note("", "Unit tests provide fast feedback");
		Set<ConstraintViolation<Note>> constraintViolations = validator.validate(note);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void shouldRaiseViolationWhenContentIsEmpty() {
		Note note = new Note("Unit Tests", "");
		Set<ConstraintViolation<Note>> constraintViolations = validator.validate(note);
		assertEquals(1, constraintViolations.size());
	}
	
	@Test
	public void shouldCountOneForContentWithSingleWord() {
		Note note = new Note("Unit Tests", "Xuxa");
		assertEquals(1, note.getWordCount());
	}
	
	@Test
	public void shouldCountWordsFromNoteContent() {
		Note note = new Note("Unit Tests",
				"Unit tests provide fast feedback, but they test only an isolated unit of code");
		assertEquals(14, note.getWordCount());
	}
}