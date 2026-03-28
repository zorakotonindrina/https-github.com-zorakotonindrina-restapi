package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.ListeCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ListeCommandeRepository extends JpaRepository<ListeCommande, Long> {

    Optional<ListeCommande> findByCommande_IdCommandeAndPlat_IdPlat(Long idCommande, Long idPlat);

    List<ListeCommande> findByCommande_IdCommande(Long idCommande);


    @Query("""
        SELECT lc.plat.idPlat as idPlat, lc.plat.nom as nomPlat, SUM(lc.quantite) as quantiteTotale
        FROM ListeCommande lc
        GROUP BY lc.plat.idPlat, lc.plat.nom
        ORDER BY SUM(lc.quantite) DESC
    """)
    List<Object[]> findTopPlats();
}