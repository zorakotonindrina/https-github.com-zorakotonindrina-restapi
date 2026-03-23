package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.Plat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatRepository extends JpaRepository<Plat, Long> {

    List<Plat> findByCategory_IdCategorie(Long idCategorie);

    List<Plat> findByCategory_IdCategorieAndDispoTrue(Long idCategorie);

    Optional<Plat> findByNom(String nom);
}