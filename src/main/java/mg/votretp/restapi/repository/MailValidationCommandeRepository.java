package mg.votretp.restapi.repository;


import mg.votretp.restapi.model.MailValidationCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailValidationCommandeRepository extends JpaRepository<MailValidationCommande, Long> {

    Optional<MailValidationCommande> findTopByEmailClientAndCodeOrderByDateCreationDesc(String emailClient, String code);

    Optional<MailValidationCommande> findTopByCommande_IdCommandeOrderByDateCreationDesc(Long idCommande);

    Optional<MailValidationCommande> findTopByCommande_IdCommandeAndCodeOrderByDateCreationDesc(Long idCommande, String code);


}