package device.microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends JpaRepository<device.microservice.entities.Device, Long> {
}
