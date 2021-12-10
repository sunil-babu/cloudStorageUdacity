package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/notes")
public class NotesController {

    private final NotesServiceImpl notesService;
    private final UserServiceImpl userService;

    @PostMapping
    public String insertNote(Note notes, Authentication authentication)
    {
        if (notes.getNoteId() != null) {
            notesService.updateNote(notes);

        }
        else {
            notes.setUserId(userService.findUserByUserName(authentication.getName()).getUserId());
            notesService.insertNote(notes);
        }

        return "redirect:/result?success";
    }

    @RequestMapping(value="/{noteId}")
    public String delete(@PathVariable("noteId") Integer noteId)
    {
        notesService.deleteNote(noteId);
        return "redirect:/result?success";
    }
}
