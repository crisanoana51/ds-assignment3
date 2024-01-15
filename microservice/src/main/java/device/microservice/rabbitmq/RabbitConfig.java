package device.microservice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {
    @Bean
    Queue queue() { return new Queue("testqueue2", true);
    }

    @Bean
    DirectExchange exchange() {return new DirectExchange("testexchange2");
    }


    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("testqueue2");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqps://gull.rmq.cloudamqp.com/eibkidpx");
        connectionFactory.setUsername("eibkidpx");
        connectionFactory.setPassword("XfXpbTcIk4j3bzdqkbu2hxOsFuvY9TYK");
        connectionFactory.setPort(5671);
        connectionFactory.setVirtualHost("eibkidpx");
        return connectionFactory;
    }

    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if(!ack){
                System.out.println("Message sent failed: " + cause);
            }
        });
        return template;
    }

}