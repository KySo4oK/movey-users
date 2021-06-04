package micro.user.service.userservice.controller;

import lombok.RequiredArgsConstructor;
import micro.user.service.userservice.entity.Swipe;
import micro.user.service.userservice.entity.User;
import micro.user.service.userservice.service.SwipeService;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/swipes")
public class SwipeController {
    private static final Logger log = Logger.getLogger(String.valueOf(SwipeController.class));
    private final SwipeService swipeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveSwipe(@RequestBody Swipe swipe) {
        String userId = String.valueOf(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
        swipe.setUserId(Long.parseLong(userId));
        log.info("saving swipe - " + swipe);
        swipeService.save(swipe);
    }
}
