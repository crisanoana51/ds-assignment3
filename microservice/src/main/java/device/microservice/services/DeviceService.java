package device.microservice.services;


import device.microservice.entities.Device;
import device.microservice.entities.Reading;
import device.microservice.repositories.DeviceRepository;
import device.microservice.repositories.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;


    @Autowired
    private ReadingRepository readingRepository;

    public DeviceService(DeviceRepository deviceRepository, ReadingRepository readingRepository) {

        this.deviceRepository = deviceRepository;
        this.readingRepository = readingRepository;
    }

    public Device addDevice(Device device){
        return deviceRepository.save(device);
    }

    public Device updateDevice(Device device){
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id){
        deviceRepository.deleteById(id);
    }

    public List<Device> findAllDevices(){
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Device is not found."));
    }


    public List<Device> getDeviceByUserId(Long id) {
        List<Device> devices = new ArrayList<>();
        List<Device> allDevices = deviceRepository.findAll();
        System.out.println(allDevices);
        for(Device d : allDevices){
            if(d.getUserId().equals(id)){
                devices.add(d);
            }
        }
        return devices;

    }

    public void deleteDeviceByUserId(Long id) {
        List<Device> devices = deviceRepository.findAll();
        for(Device d : devices) {
            if (d.getUserId().equals(id)) {
                deviceRepository.deleteById(d.getId());
            }
        }
    }

    public Device addReading(Long deviceId, Long readingId) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        Optional<Reading> reading = readingRepository.findById(readingId);

        if (device.isPresent() && reading.isPresent()){
            reading.get().setDevice(device.get());
        }

        return device.get();
    }
}
