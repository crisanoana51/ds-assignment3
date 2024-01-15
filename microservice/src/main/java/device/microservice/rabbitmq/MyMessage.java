package device.microservice.rabbitmq;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = MyMessage.class)
public class MyMessage {

    private Timestamp timestamp;
    private Double consumption;
    private Long deviceId;

    public MyMessage(Timestamp timestamp, Double consumption, Long deviceId) {
        this.timestamp = timestamp;
        this.consumption = consumption;
        this.deviceId = deviceId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString(){
        return "The Message: [deviceId=" + deviceId +", consumption=" + consumption + ", timestamp=" + timestamp +"]";
    }
}
