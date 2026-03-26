package mg.votretp.restapi.dto;

import org.springframework.hateoas.RepresentationModel;

public class PasserCommandeResponseDTO extends RepresentationModel<PasserCommandeResponseDTO> {

    private Long idCommande;
    private Integer numCommande;
    private String status;
    private String emailClient;
    private String numClient;
    private String modePaiement;
    private String message;
    private String codeValidation;

    public PasserCommandeResponseDTO() {
    }

    public PasserCommandeResponseDTO(Long idCommande, Integer numCommande, String status,
                                     String emailClient, String numClient,
                                     String modePaiement, String message,
                                     String codeValidation) {
        this.idCommande = idCommande;
        this.numCommande = numCommande;
        this.status = status;
        this.emailClient = emailClient;
        this.numClient = numClient;
        this.modePaiement = modePaiement;
        this.message = message;
        this.codeValidation = codeValidation;
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

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodeValidation() {
        return codeValidation;
    }

    public void setCodeValidation(String codeValidation) {
        this.codeValidation = codeValidation;
    }
}