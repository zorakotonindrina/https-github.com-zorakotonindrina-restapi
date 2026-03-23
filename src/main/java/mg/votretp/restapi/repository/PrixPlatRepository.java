package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.PrixPlat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrixPlatRepository extends JpaRepository<PrixPlat, Long> {

    Optional<PrixPlat> findTopByPlat_IdPlatOrderByDatePrixDesc(Long idPlat);

}