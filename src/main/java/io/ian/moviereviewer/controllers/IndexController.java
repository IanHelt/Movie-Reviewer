package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private MovieRepository movieRepo;

    @RequestMapping(value = "/")
    public String index(Model model){
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
