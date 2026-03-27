package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Long> {
}