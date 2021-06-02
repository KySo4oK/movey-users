package micro.user.service.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello! User microservice is on Kubernetes now!";
    }

    @GetMapping("/hello/secured")
    public String sayHelloSecured() {
        return "Hello! User microservice is on Kubernetes now!";
    }
}
