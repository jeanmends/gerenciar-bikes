package bikes.bike_manager.repository;

import bikes.bike_manager.model.Bikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BikesRepository extends JpaRepository<Bikes, Long> {


}
