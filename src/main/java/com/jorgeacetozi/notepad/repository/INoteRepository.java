package com.jorgeacetozi.notepad.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.jorgeacetozi.notepad.model.Note;

public interface INoteRepository extends ListCrudRepository<Note, Integer> {

}
