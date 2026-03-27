package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.MontantParModePaiementDTO;
import mg.votretp.restapi.dto.TopPlatDTO;
import mg.votretp.restapi.dto.TotalParModePaiementDTO;
import mg.votretp.restapi.repository.ListeCommandeRepository;
import mg.votretp.restapi.repository.RecuRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatistiqueService {

    private final ListeCommandeRepository listeCommandeRepository;
    private final RecuRepository recuRepository;

    public StatistiqueService(ListeCommandeRepository listeCommandeRepository,
                              RecuRepository recuRepository) {
        this.listeCommandeRepository = listeCommandeRepository;
        this.recuRepository = recuRepository;
    }

    public List<TopPlatDTO> getTopPlats() {
        return listeCommandeRepository.findTopPlats().stream()
                .map(obj -> new TopPlatDTO(
                        (Long) obj[0],
                        (String) obj[1],
                        ((Number) obj[2]).longValue()
                ))
                .toList();
    }

    public List<TotalParModePaiementDTO> getTotalParModePaiement() {
        return recuRepository.findTotalParModePaiement().stream()
                .map(obj -> new TotalParModePaiementDTO(
                        (String) obj[0],
                        (BigDecimal) obj[1]
                ))
                .toList();
    }



    public List<MontantParModePaiementDTO> getMontantParModePaiementEntreDeuxDates(
            java.time.LocalDate dateDebut,
            java.time.LocalDate dateFin
    ) {
        if (dateDebut == null || dateFin == null) {
            throw new RuntimeException("Les deux dates sont obligatoires");
        }

        if (dateFin.isBefore(dateDebut)) {
            throw new RuntimeException("La date de fin doit être supérieure ou égale à la date de début");
        }

        java.time.LocalDateTime debut = dateDebut.atStartOfDay();
        java.time.LocalDateTime fin = dateFin.plusDays(1).atStartOfDay();

        return recuRepository.findMontantTotalParModePaiementEntreDeuxDates(debut, fin)
                .stream()
                .map(obj -> new MontantParModePaiementDTO(
                        (String) obj[0],
                        (java.math.BigDecimal) obj[1]
                ))
                .toList();
    }
}