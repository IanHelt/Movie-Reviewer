package io.ian.moviereviewer.repositories;

import io.ian.moviereviewer.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{
}
