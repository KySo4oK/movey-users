package micro.user.service.userservice.controller;

import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import micro.user.service.userservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{movieId}")
    public ResponseEntity<ReviewResultsPage> getMovieReviews(@PathVariable Integer movieId) {
        return reviewService.getMovieReviews(movieId);
    }
}
