package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.models.User;
import io.ian.moviereviewer.repositories.MovieRepository;
import io.ian.moviereviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ModifyController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/modify/{id}")
    public String modify(Model model,
                         @PathVariable("id") Long id,
                         Principal principal){
        User me = userRepo.findByUsername(principal.getName());
        Movie toModify = movieRepo.findOne(id);
        model.addAttribute("movie", toModify);
        return "modify";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String doModify(@ModelAttribute Movie movie,
                           Principal principal) {
        User me = userRepo.findByUsername(principal.getName());
        movieRepo.save(movie);
        return "redirect:/";
    }

    }

