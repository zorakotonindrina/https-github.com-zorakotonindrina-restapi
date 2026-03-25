package mg.votretp.restapi.service;

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
}