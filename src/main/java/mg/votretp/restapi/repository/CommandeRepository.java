package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Optional<Commande> findByEmailClientAndStatus(String emailClient, String status);
}