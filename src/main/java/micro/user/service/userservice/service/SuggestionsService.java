package micro.user.service.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "suggestions-service", url = "notification-service-service.default.svc.cluster.local")
public interface SuggestionsService {
    @GetMapping(value = "/notification/hello")
    String hello();
}
