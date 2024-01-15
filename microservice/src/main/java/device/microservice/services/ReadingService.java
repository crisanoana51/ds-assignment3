package device.microservice.services;


import device.microservice.entities.Device;
import device.microservice.entities.Reading;
import device.microservice.repositories.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {


    @Autowired
    private ReadingRepository readingRepository;


    public ReadingService(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    public List<Reading> findAllReadings(){
        return readingRepository.findAll();
    }

    public Reading addReading(Reading reading){
        return readingRepository.save(reading);
    }

    public Reading updateReading(Reading reading){
        return readingRepository.save(reading);
    }

    public void deleteReading(Long id){
        readingRepository.deleteById(id);
    }

    public List<Reading> getReadingsByDeviceId(Long id){
        List<Reading> readings = readingRepository.findReadingsByDeviceId(id);
        return readings;
    }

    public Reading getReadingById(Long id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Reading is not found."));
    }
}
