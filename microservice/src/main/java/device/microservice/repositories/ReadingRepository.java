package device.microservice.repositories;

import device.microservice.entities.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findReadingsByDeviceId(Long id);
}
