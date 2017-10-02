package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.User;
import io.ian.moviereviewer.repositories.RoleRepository;
import io.ian.moviereviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @RequestMapping(value = "/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup/tryagain")
    public String signupFormError(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("invalid", "Username taken");
        return "signup";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUserForm(@ModelAttribute User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return "redirect:/signup/tryagain";
        } else {
            user.setRole(roleRepo.findOne((long) 1));
            userRepo.save(user);
            return "redirect:/login/createSuccess";
        }
    }
}
