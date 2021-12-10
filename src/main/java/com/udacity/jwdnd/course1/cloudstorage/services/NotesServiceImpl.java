package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotesServiceImpl implements  NotesService{

    private final NoteMapper noteMapper;

    @Override
    public void insertNote(Note note) {
        noteMapper.insert(note);
    }

    @Override
    public List<Note> findAllNotes(Integer userId) {
        return noteMapper.findAllByUserId(userId);
    }

    @Override
    public void deleteNote(Integer noteId) {
        noteMapper.delete(noteId);
    }

    @Override
    public void updateNote(Note note) {
        noteMapper.update(note);
    }
}
