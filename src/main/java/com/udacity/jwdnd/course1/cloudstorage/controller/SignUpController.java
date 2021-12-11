package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/signup")
public class SignUpController {

    private final UserServiceImpl usersService;

    @GetMapping
    public String getSignUpPage(@ModelAttribute User user) {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        try {
            usersService.registerUser(user);
        } catch (Exception e) {
            return "redirect:signup?error";
        }
        redirectAttributes.addFlashAttribute("SuccessMessage","Sign Up Successfully");
        return "redirect:/login";
    }

}
