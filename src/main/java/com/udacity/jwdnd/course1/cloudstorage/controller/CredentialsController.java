package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialsServiceImpl credentialsService;
    private final UserServiceImpl userService;

    @PostMapping
    public String insertOrUpdateCredentials(Credential credential, Authentication authentication) {
        if (credential.getCredentialId() != null) {
            credentialsService.updateCredential(credential);
        }
        else {
            credential.setUserId(userService.findUserByUserName(authentication.getName()).getUserId());
            credentialsService.insertCredential(credential);
        }
        return "redirect:/result?success";
    }


    @GetMapping("/{credentialId}")
    public String deleteCredentials(@PathVariable("credentialId") Integer credentialId) {

        credentialsService.deleteCredential(credentialId);
        return "redirect:/result?success";

    }

}
