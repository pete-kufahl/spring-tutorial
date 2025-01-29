package net.prk.email_service.kafka;

import net.prk.base_domains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent) {
        LOG.info(String.format("order event received in email-service => %s", orderEvent.toString()));
        var order = orderEvent.getOrder();
        LOG.info(String.format("order id: %s, %s, %d @ %.2f", order.getOrderId(),
                order.getName(),
                order.getQty(),
                order.getPrice()));
        // send the email to the customer
    }
}
