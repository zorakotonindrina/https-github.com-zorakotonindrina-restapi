package mg.votretp.restapi.service;


import mg.votretp.restapi.dto.PrixPlatCreateDTO;
import mg.votretp.restapi.model.Plat;
import mg.votretp.restapi.model.PrixPlat;
import mg.votretp.restapi.repository.PlatRepository;
import mg.votretp.restapi.repository.PrixPlatRepository;
import org.springframework.stereotype.Service;

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
}