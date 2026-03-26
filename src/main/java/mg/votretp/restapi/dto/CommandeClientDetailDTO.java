package mg.votretp.restapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CommandeClientDetailDTO {

    private Integer numCommande;
    private String status;
    private String emailClient;
    private String numClient;
    private LocalDateTime dateCommande;
    private String modePaiement;
    private String refPaiement;
    private Double total;
    private List<CommandeClientLigneDTO> lignes;

    public CommandeClientDetailDTO() {}

    public CommandeClientDetailDTO(Integer numCommande, String status, String emailClient,
                                   String numClient, LocalDateTime dateCommande,
                                   String modePaiement, String refPaiement,
                                   Double total, List<CommandeClientLigneDTO> lignes) {
        this.numCommande = numCommande;
        this.status = status;
        this.emailClient = emailClient;
        this.numClient = numClient;
        this.dateCommande = dateCommande;
        this.modePaiement = modePaiement;
        this.refPaiement = refPaiement;
        this.total = total;
        this.lignes = lignes;
    }

    // getters/setters
}