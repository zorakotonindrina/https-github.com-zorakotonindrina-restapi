package mg.votretp.restapi.repository;

import mg.votretp.restapi.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Optional<Commande> findByEmailClientAndStatus(String emailClient, String status);


    @Query("""
        SELECT COALESCE(MAX(c.numCommande), 0)
        FROM Commande c
        WHERE c.dateCommande >= :debutJour
          AND c.dateCommande < :finJour
    """)
    Integer findMaxNumCommandeByDay(LocalDateTime debutJour, LocalDateTime finJour);
}