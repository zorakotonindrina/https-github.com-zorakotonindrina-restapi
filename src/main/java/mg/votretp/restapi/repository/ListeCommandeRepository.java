package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.ListeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListeCommandeRepository extends JpaRepository<ListeCommande, Long> {

    Optional<ListeCommande> findByCommande_IdCommandeAndPlat_IdPlat(Long idCommande, Long idPlat);

    List<ListeCommande> findByCommande_IdCommande(Long idCommande);
}