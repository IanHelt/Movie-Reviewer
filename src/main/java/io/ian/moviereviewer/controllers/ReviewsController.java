package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.models.Review;
import io.ian.moviereviewer.models.User;
import io.ian.moviereviewer.repositories.MovieRepository;
import io.ian.moviereviewer.repositories.ReviewRepository;
import io.ian.moviereviewer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ReviewsController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/reviews/{id}")
    public String modify(Model model,
                         @PathVariable("id") Long id,
                         Principal principal){
        try {
            User me = userRepo.findByUsername(principal.getName());
            Movie toReview = movieRepo.findOne(id);
            model.addAttribute("movie", toReview);
            return "reviews";
        } catch (NullPointerException Exception) {
            Movie toReview = movieRepo.findOne(id);
            model.addAttribute("movie", toReview);
            return "reviews";
        }
    }

    @RequestMapping(value = "/addReview/{id}", method = RequestMethod.POST)
    public String rate(@RequestParam("rating") int rating,
                       @PathVariable("id") Long id,
                       Principal principal) {
        User me = userRepo.findByUsername(principal.getName());
        for (Review review : me.getReviews()) {
            if (review.getMovie().getId() == id && review.getUser() == me) {
                return "redirect:/reviews/" + id;
            }
        }
        System.out.println(rating);
        Review newReview = new Review();
        Movie movie = movieRepo.findOne(id);
        newReview.setUser(me);
        newReview.setMovie(movie);
        //Reviewer is redundant, represents user.username
        newReview.setReviewer(me.getUsername());
        newReview.setRating(rating);
        reviewRepo.save(newReview);
        return "redirect:/";
    }

}
