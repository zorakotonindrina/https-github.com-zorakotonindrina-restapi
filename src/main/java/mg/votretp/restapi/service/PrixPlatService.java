package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.PrixPlatCreateDTO;
import mg.votretp.restapi.dto.PrixPlatResponseDTO;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.repository.PlatRepository;
import mg.votretp.restapi.repository.PrixPlatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrixPlatService {

    private final PrixPlatRepository prixPlatRepository;
    private final PlatRepository platRepository;

    public PrixPlatService(PrixPlatRepository prixPlatRepository, PlatRepository platRepository) {
        this.prixPlatRepository = prixPlatRepository;
        this.platRepository = platRepository;
    }

    public PrixPlat createPrixPlat(PrixPlatCreateDTO dto) {
        Plat plat = platRepository.findById(dto.getIdPlat())
                .orElseThrow(() -> new RuntimeException("Plat introuvable"));

        PrixPlat prixPlat = new PrixPlat();
        prixPlat.setPrix(dto.getPrix());
        prixPlat.setPlat(plat);

        return prixPlatRepository.save(prixPlat);
    }

    public List<PrixPlatResponseDTO> getAllPrixPlats() {
        List<PrixPlat> list = prixPlatRepository.findAll();

        return list.stream().map(p -> new PrixPlatResponseDTO(
                p.getIdPrix(),
                p.getPrix().doubleValue(),
                p.getDatePrix(),
                p.getPlat().getIdPlat(),
                p.getPlat().getNom()
        )).toList();
    }

    public PrixPlatResponseDTO getPrixPlatById(Long id) {
        PrixPlat p = prixPlatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prix plat introuvable"));

        return new PrixPlatResponseDTO(
                p.getIdPrix(),
                p.getPrix().doubleValue(),
                p.getDatePrix(),
                p.getPlat().getIdPlat(),
                p.getPlat().getNom()
        );
    }

    public PrixPlat updatePrixPlat(Long id, PrixPlatCreateDTO dto) {
        PrixPlat prixPlat = prixPlatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prix introuvable"));

        Plat plat = platRepository.findById(dto.getIdPlat())
                .orElseThrow(() -> new RuntimeException("Plat introuvable"));

        prixPlat.setPrix(dto.getPrix());
        prixPlat.setPlat(plat);

        return prixPlatRepository.save(prixPlat);
    }

    public String deletePrixPlat(Long id) {
        PrixPlat prixPlat = prixPlatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prix introuvable"));

        prixPlatRepository.delete(prixPlat);
        return "Prix supprimé avec succès";
    }
}