package device.microservice.rabbitmq;


import device.microservice.entities.Device;
import device.microservice.entities.Reading;
import device.microservice.payload.request.ReadingRequest;
import device.microservice.services.DeviceService;
import device.microservice.services.ReadingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    private ReadingService readingService;


    @Autowired
    private DeviceService deviceService;

    public void sendMessage(Device device) {
        Timestamp timestamp = Timestamp.from(Instant.now());
        MyMessage reading = new MyMessage(timestamp, device.getMaximumHourlyEnergyConsumption(), device.getId());
        rabbitTemplate.convertAndSend("testqueue2", device.getMaximumHourlyEnergyConsumption());
    }
}
