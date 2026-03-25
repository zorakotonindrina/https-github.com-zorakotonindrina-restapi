package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.CommandeConfirmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeConfirmerRepository extends JpaRepository<CommandeConfirmer, Long> {

    Optional<CommandeConfirmer> findByCommande_IdCommande(Long idCommande);
}