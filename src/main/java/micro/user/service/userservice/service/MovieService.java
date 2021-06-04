package micro.user.service.userservice.service;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "movie-service", url = "movie-service-service.default.svc.cluster.local")

public interface MovieService {
    @GetMapping("/movie/fetch")
    ResponseEntity<MovieResultsPage> fetchMovies();

    @GetMapping("/movie/saved/{userId}")
    ResponseEntity<List<BaseMovie>> getSavedMoviesByUser(@PathVariable("userId") Integer userId);
}
