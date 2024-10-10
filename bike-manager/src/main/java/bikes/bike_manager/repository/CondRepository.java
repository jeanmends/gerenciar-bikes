package bikes.bike_manager.repository;

import bikes.bike_manager.model.Cond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondRepository extends JpaRepository <Cond, Long> {
}
