package mg.votretp.restapi.repository;

import mg.votretp.restapi.dto.ProductDTO;
import mg.votretp.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
