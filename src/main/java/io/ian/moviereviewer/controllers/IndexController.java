package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.models.User;
import io.ian.moviereviewer.repositories.MovieRepository;
import io.ian.moviereviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/")
    public String index(Model model,
                        Principal principal){
        User me = userRepo.findByUsername(principal.getName());
        model.addAttribute("movie", new Movie());
        model.addAttribute("allMovies", movieRepo.findAll());
        return "index";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.POST)
    public String toAdd(){
        return "redirect:/add";
    }

    @RequestMapping(value = "/toReviews", method = RequestMethod.POST)
    public String toReviews(){
        return "redirect:/reviews";
    }

    @RequestMapping(value = "/toIndex", method = RequestMethod.POST)
    public String toIndex(){
        return "redirect:/";
    }

}
