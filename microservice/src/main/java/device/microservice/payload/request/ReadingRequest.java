package device.microservice.payload.request;

import java.sql.Timestamp;

public class ReadingRequest {

    private Long id;
    private Timestamp timestamp;
    private Double consumption;

    public ReadingRequest() {
    }

    public ReadingRequest(Timestamp timestamp, Double consumption){
        this.consumption = consumption;
        this.timestamp = timestamp;
    }

    public ReadingRequest(Long id, Timestamp timestamp, Double consumption) {
        this.id = id;
        this.timestamp = timestamp;
        this.consumption = consumption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
