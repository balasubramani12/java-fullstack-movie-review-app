package bsm_movie_ra.movies.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bsm_movie_ra.movies.document.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
