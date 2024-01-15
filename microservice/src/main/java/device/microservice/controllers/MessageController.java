package device.microservice.controllers;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    @Autowired
    private RabbitTemplate rabbitTemplate;



    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(String message){
        rabbitTemplate.convertAndSend("/message.readValue", message);
        return new ResponseEntity<>("Send", HttpStatus.OK);
    }


    @SendTo("/message/readValue")
    public String broadCastMessage(@Payload String message){
        return message;
    }
}
