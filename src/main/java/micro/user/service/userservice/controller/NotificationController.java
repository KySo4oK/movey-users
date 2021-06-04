package micro.user.service.userservice.controller;

import lombok.RequiredArgsConstructor;
import micro.user.service.userservice.entity.User;
import micro.user.service.userservice.service.SuggestionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final SuggestionsService service;

    @GetMapping("/rabbit")
    ResponseEntity getRabbit() {
        return service.getRabbit();
    }

    @GetMapping("/hello")
    public String helloFromSuggestions() {
        return service.hello();
    }

    @PostMapping(value = "/savings/{movieId}/")
    ResponseEntity postSaving(@PathVariable("movieId") String movieId) {
        return service.postSaving(movieId, String.valueOf(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId()));
    }

    @GetMapping(value = "/savings/")
    ResponseEntity getSavings() {
        String userId = String.valueOf(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
        return service.getSavings(userId);
    }
}
