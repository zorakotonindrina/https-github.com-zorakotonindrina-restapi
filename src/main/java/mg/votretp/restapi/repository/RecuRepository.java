package mg.votretp.restapi.repository;


import mg.votretp.restapi.model.Recu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RecuRepository extends JpaRepository<Recu, Long> {

    Optional<Recu> findByCommande_IdCommande(Long idCommande);

    @Query("""
        SELECT r.commande.modePaiement.nom as modePaiement, SUM(r.prixTotal) as montantTotal
        FROM Recu r
        WHERE r.commande.modePaiement IS NOT NULL
        GROUP BY r.commande.modePaiement.nom
        ORDER BY SUM(r.prixTotal) DESC
    """)
    List<Object[]> findTotalParModePaiement();


    @Query("""
        SELECT r.commande.modePaiement.nom, SUM(r.prixTotal)
        FROM Recu r
        WHERE r.commande.dateCommande >= :dateDebut
          AND r.commande.dateCommande < :dateFin
        GROUP BY r.commande.modePaiement.nom
        ORDER BY SUM(r.prixTotal) DESC
    """)
    List<Object[]> findMontantTotalParModePaiementEntreDeuxDates(LocalDateTime dateDebut, LocalDateTime dateFin);

}