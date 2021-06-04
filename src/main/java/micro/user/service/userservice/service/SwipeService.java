package micro.user.service.userservice.service;

import lombok.RequiredArgsConstructor;
import micro.user.service.userservice.entity.Swipe;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwipeService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void save(Swipe swipe) {
        rabbitTemplate.convertAndSend("user" + ".exchange", "user" + ".routingkey", swipe);
    }
}
