package device.microservice.entities;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp timestamp;

    private Double maximumHourlyEnergyConsumption;

    @ManyToOne
    private Device device;

    public Reading(Long id, Timestamp timestamp, Double maximumHourlyEnergyConsumption, Device device) {
        this.id = id;
        this.timestamp = timestamp;
        this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
        this.device = device;
    }

    public Reading(Timestamp timestamp, Double maximumHourlyEnergyConsumption) {
        this.timestamp = timestamp;
        this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
    }

    public Reading() {

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

    public Double getMaximumHourlyEnergyConsumption() {
        return maximumHourlyEnergyConsumption;
    }

    public void setMaximumHourlyEnergyConsumption(Double maximumHourlyEnergyConsumption) {
        this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
