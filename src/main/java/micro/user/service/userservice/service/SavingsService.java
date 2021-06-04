package micro.user.service.userservice.service;

import lombok.RequiredArgsConstructor;
import micro.user.service.userservice.entity.Saving;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsService {
    //
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    public void save(Saving saving) {
        rabbitTemplate.convertAndSend("user" + ".exchange", "user" + ".routingkey", saving);
    }
}
