package io.ian.moviereviewer.controllers;

import io.ian.moviereviewer.models.Movie;
import io.ian.moviereviewer.models.Review;
import io.ian.moviereviewer.repositories.MovieRepository;
import io.ian.moviereviewer.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewsController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @RequestMapping(value = "/reviews/{id}")
    public String modify(Model model,
                         @PathVariable("id") Long id){
        Movie toReview = movieRepo.findOne(id);
        model.addAttribute("movie", toReview);
        return "reviews";
    }

    @RequestMapping(value = "/addReview/{id}", method = RequestMethod.POST)
    public String rate(@RequestParam("reviewer") String reviewer,
                       @RequestParam("rating") int rating,
                       @PathVariable("id") Long id) {
        System.out.println(reviewer);
        System.out.println(rating);
        Review newReview = new Review();
        Movie movie = movieRepo.findOne(id);
        newReview.setMovie(movie);
        newReview.setReviewer(reviewer);
        newReview.setRating(rating);
        reviewRepo.save(newReview);
        return "redirect:/";
    }

}
