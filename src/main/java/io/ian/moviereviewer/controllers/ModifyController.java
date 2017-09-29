package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModifyController {

    @Autowired
    private MovieRepository movieRepo;

    @RequestMapping(value = "/modify/{id}")
    public String modify(Model model,
                         @PathVariable("id") Long id){
        Movie toModify = movieRepo.findOne(id);
        model.addAttribute("movie", toModify);
        return "modify";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String doModify(@ModelAttribute Movie movie) {
        movieRepo.save(movie);
        return "redirect:/";
    }

    }

