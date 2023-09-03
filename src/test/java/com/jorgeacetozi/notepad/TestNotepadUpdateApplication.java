package com.jorgeacetozi.notepad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestNotepadUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.from(NotepadUpdateApplication::main).with(TestNotepadUpdateApplication.class).run(args);
	}

}
