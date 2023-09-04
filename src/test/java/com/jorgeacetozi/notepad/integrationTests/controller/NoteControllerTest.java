package com.jorgeacetozi.notepad.integrationTests.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorgeacetozi.notepad.model.Note;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
public class NoteControllerTest {

	@Autowired 
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void shouldCreateNoteWithTitleAndContentAndReturnHttp201Created() throws JsonProcessingException, Exception {
		Note note = new Note("Integration Test", "Test external integrations");
		
		this.mockMvc.perform(post("/notes")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(mapper.writeValueAsString(note)))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.id", is(notNullValue())))
					.andExpect(jsonPath("$.title").value(note.getTitle()))
					.andExpect(jsonPath("$.content").value(note.getContent()))
					.andExpect(jsonPath("$.wordCount").value(note.getWordCount()));
	}
	
	@Test
	public void shouldNotCreateNoteWhenTitleIsEmptyAndReturnHttp400BadRequest() throws JsonProcessingException, Exception {
		Note note = new Note("", "Test external integrations");
		
		this.mockMvc.perform(post("/notes")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(mapper.writeValueAsString(note)))
					.andDo(print())
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void shouldNotCreateNotWhenContentIsEmptyAndReturnHttp400BadRequest() throws JsonProcessingException, Exception {
		Note note = new Note("Integration Test", "");
		
		this.mockMvc.perform(post("/notes")
							 .contentType(MediaType.APPLICATION_JSON)
							 .content(mapper.writeValueAsString(note)))
					.andDo(print())
					.andExpect(status().isBadRequest());
			
	}
}
