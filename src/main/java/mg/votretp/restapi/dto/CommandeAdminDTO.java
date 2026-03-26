package mg.votretp.restapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CommandeAdminDTO {

    private Long idCommande;
    private Integer numCommande;
    private String emailClient;
    private String numClient;
    private String status;
    private LocalDateTime dateCommande;
    private String refPaiement;
    private Double total;
    private List<CommandeAdminLigneDTO> lignes;

    public CommandeAdminDTO() {}

    public CommandeAdminDTO(Long idCommande, Integer numCommande, String emailClient,
                            String numClient, String status, LocalDateTime dateCommande,
                            String refPaiement, Double total,
                            List<CommandeAdminLigneDTO> lignes) {
        this.idCommande = idCommande;
        this.numCommande = numCommande;
        this.emailClient = emailClient;
        this.numClient = numClient;
        this.status = status;
        this.dateCommande = dateCommande;
        this.refPaiement = refPaiement;
        this.total = total;
        this.lignes = lignes;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public Integer getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(Integer numCommande) {
        this.numCommande = numCommande;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
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

    public List<CommandeAdminLigneDTO> getLignes() {
        return lignes;
    }

    public void setLignes(List<CommandeAdminLigneDTO> lignes) {
        this.lignes = lignes;
    }

    // getters/setters
}