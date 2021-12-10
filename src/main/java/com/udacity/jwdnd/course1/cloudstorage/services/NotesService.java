package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import java.util.List;

public interface NotesService {

     void insertNote(Note note);
     List<Note> findAllNotes(Integer userId);
     void deleteNote(Integer noteId);
     void updateNote(Note note);

}
