package io.evgeny.project3restConsumer.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.evgeny.project3restConsumer.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);

}
