package micro.user.service.userservice.controller;

import lombok.RequiredArgsConstructor;
import micro.user.service.userservice.entity.Saving;
import micro.user.service.userservice.service.SavingsService;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/savings")
public class SavingController {
    private static final Logger log = Logger.getLogger(String.valueOf(SavingController.class));
    private final SavingsService service;

    @PostMapping("/{userId}/{movieId}")
    void saveSwipe(@PathVariable("userId") String userId, @PathVariable("movieId") String movieId) {
        log.info("saving swipe for user - " + userId + " and movie - " + movieId);
        service.save(new Saving(userId, movieId));
    }
}
