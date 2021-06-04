package micro.user.service.userservice.controller;


import micro.user.service.userservice.service.SuggestionsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class HelloController {

    private static final Logger log = Logger.getLogger(String.valueOf(HelloController.class));

    String message;

    DataSource dataSource;

    private boolean fail;

    @Autowired
    SuggestionsService service;

    @Autowired
    public HelloController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        log.info("called hello endpoint");
        if (fail) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Hello! User microservice is on Kubernetes now!" + Instant.now() + message, HttpStatus.OK);
    }

    @GetMapping("/hello/s")
    public String helloFromSuggestions() {
        return service.hello();
    }

    @GetMapping("/db")
    public String dbTest() {
        log.warning("called db endpoint");
        try {
            return dataSource.getConnection().getSchema();
        } catch (Exception throwables) {
            return "db not connected " + throwables.getMessage();
        }
    }

    @GetMapping("/fail")
    public void fail() {
        fail = true;
    }

    @GetMapping("/live")
    public void live() {
        fail = false;
    }

    //
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;


    @GetMapping("/emit/{service}/{message}")
    String emitToMovieQueue(@PathVariable(name = "message") String message,
                            @PathVariable(name = "service") String service) {
        rabbitTemplate.convertAndSend(service + ".exchange", service + ".routingkey", message);
        return "Message sent";
    }
}
