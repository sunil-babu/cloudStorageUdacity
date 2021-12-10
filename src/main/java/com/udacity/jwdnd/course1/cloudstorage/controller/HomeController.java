package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.FileServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final CredentialsServiceImpl credentialsService;
    private final NotesServiceImpl notesService;
    private final FileServiceImpl filesService;
    private final UserServiceImpl userService;

    @GetMapping
    public String getHomePage(Authentication auth, Model model) {

        int userid = userService.findUserByUserName(auth.getName()).getUserId();
        List<Credential> credentialsList = credentialsService.findAllCredentials(userid);
        model.addAttribute("credentialsList", credentialsList);
        List<Note> notesList = notesService.findAllNotes(userid);
        model.addAttribute("notesList", notesList);
        List<File> fileList = filesService.findAllFiles(userid);
        model.addAttribute("fileList", fileList);
        return "home";
    }
}
