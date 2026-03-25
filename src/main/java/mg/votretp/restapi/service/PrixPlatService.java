package mg.votretp.restapi.service;

import mg.votretp.restapi.dto.PrixPlatCreateDTO;
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

    public List<PrixPlat> getAllPrixPlats() {
        return prixPlatRepository.findAll();
    }

    public PrixPlat getPrixPlatById(Long id) {
        return prixPlatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prix introuvable"));
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