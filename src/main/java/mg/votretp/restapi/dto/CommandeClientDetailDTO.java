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

    public CommandeClientDetailDTO() {
    }

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

    public Integer getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(Integer numCommande) {
        this.numCommande = numCommande;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getRefPaiement() {
        return refPaiement;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<CommandeClientLigneDTO> getLignes() {
        return lignes;
    }

    public void setLignes(List<CommandeClientLigneDTO> lignes) {
        this.lignes = lignes;
    }
}