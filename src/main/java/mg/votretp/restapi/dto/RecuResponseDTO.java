package mg.votretp.restapi.dto;

import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

public class RecuResponseDTO extends RepresentationModel<RecuResponseDTO> {

    private Long idCommande;
    private Integer numCommande;
    private String emailClient;
    private String refPaiement;
    private BigDecimal prixTotal;
    private String status;
    private String message;

    public RecuResponseDTO() {
    }

    public RecuResponseDTO(Long idCommande, Integer numCommande, String emailClient,
                           String refPaiement, BigDecimal prixTotal,
                           String status, String message) {
        this.idCommande = idCommande;
        this.numCommande = numCommande;
        this.emailClient = emailClient;
        this.refPaiement = refPaiement;
        this.prixTotal = prixTotal;
        this.status = status;
        this.message = message;
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

    public String getRefPaiement() {
        return refPaiement;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}