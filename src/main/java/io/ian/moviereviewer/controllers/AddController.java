package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddController {

    @Autowired
    private MovieRepository movieRepo;

    @RequestMapping(value = "/add")
    public String add(Model model){
        model.addAttribute("movie", new Movie());
        return "add";
    }

    @RequestMapping(value = "/newMovie/", method = RequestMethod.POST)
    public String addMovie(@ModelAttribute Movie movie) {
        System.out.println(movie);
        movieRepo.save(movie);
        return "redirect:/";
    }


}
