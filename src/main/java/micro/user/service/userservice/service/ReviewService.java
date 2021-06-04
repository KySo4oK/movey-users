package micro.user.service.userservice.service;

import com.uwetrottmann.tmdb2.entities.ReviewResultsPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "review-service", url = "review-service-service.default.svc.cluster.local")
public interface ReviewService {
    @GetMapping("/review/{movieId}")
    ResponseEntity<ReviewResultsPage> getMovieReviews(@PathVariable Integer movieId);
}
