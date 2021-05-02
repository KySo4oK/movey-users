package micro.user.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.time.Instant;

@RestController
@RequestMapping("/user")
public class HelloController {
    String message;

    @Autowired
    CustomDataSourceConfig customDataSourceConfig;

    DataSource dataSource;

    private boolean fail;

    RabbitMqReceiver rabbitMqReceiver;

    @Autowired
    public HelloController(DataSource dataSource, RabbitMqReceiver rabbitMqReceiver) {
        this.dataSource = dataSource;
        this.rabbitMqReceiver = rabbitMqReceiver;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        if (fail) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Hello! User microservice is on Kubernetes now!" + Instant.now() + message, HttpStatus.OK);
    }

    @GetMapping("/db")
    public String dbTest(){
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

    @GetMapping("/rabbit")
    public String listenRabbit() {
        try {
            return rabbitMqReceiver.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "listener error";
        }
    }
}
