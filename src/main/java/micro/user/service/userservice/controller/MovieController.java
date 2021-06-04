package micro.user.service.userservice.controller;

import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import lombok.RequiredArgsConstructor;
import micro.user.service.userservice.entity.User;
import micro.user.service.userservice.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/fetch")
    public ResponseEntity<MovieResultsPage> fetchMovies() {
        return movieService.fetchMovies();
    }

    @GetMapping("/saved")
    public ResponseEntity<List<BaseMovie>> getSavedMoviesByUser() {
        String userId = String.valueOf(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
        return movieService.getSavedMoviesByUser(Integer.parseInt(userId));
    }
}
