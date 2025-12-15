package bsm_movie_ra.movies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;

import bsm_movie_ra.movies.document.Movie;
import bsm_movie_ra.movies.document.Review;
import bsm_movie_ra.movies.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {

        System.out.println("Inside createReview()");
        System.out.println("reviewBody = " + reviewBody);
        System.out.println("imdbId     = " + imdbId);

        Review review = reviewRepository.insert(new Review(reviewBody));
        System.out.println("Saved review with id = " + review.getId());

        UpdateResult result = mongoTemplate.update(Movie.class)
            .matching(Criteria.where("imdbId").is(imdbId))
            .apply(new Update().push("reviewIds").value(review))
            .first();

        System.out.println("Matched movies   = " + result.getMatchedCount());
        System.out.println("Modified movies  = " + result.getModifiedCount());

        return review;
    }
}
