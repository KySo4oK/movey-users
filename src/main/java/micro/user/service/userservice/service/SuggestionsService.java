package micro.user.service.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "suggestions-service", url = "notification-service-service.default.svc.cluster.local")
public interface SuggestionsService {
    @GetMapping(value = "/notification/hello")
    String hello();

    @GetMapping(value = "/notification/rabbit")
    ResponseEntity getRabbit();

    @PostMapping(value = "/notification/savings/{userId}/{movieId}/")
    ResponseEntity postSaving(@PathVariable("movieId") String movieId, @PathVariable("userId") String userId);

    @GetMapping(value = "/notification/savings/{userId}/")
    ResponseEntity getSavings(@PathVariable("userId") String userId);
}
