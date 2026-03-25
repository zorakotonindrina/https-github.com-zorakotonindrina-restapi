package mg.votretp.restapi.repository;


import mg.votretp.restapi.model.ModePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModePaiementRepository extends JpaRepository<ModePaiement, Long> {
    Optional<ModePaiement> findByNom(String nom);
}