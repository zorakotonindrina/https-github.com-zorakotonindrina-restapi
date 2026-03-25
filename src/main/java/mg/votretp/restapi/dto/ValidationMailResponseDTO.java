package mg.votretp.restapi.dto;

import org.springframework.hateoas.RepresentationModel;

public class ValidationMailResponseDTO extends RepresentationModel<ValidationMailResponseDTO> {

    private Long idCommande;
    private String status;
    private String emailClient;
    private String message;

    public ValidationMailResponseDTO() {
    }

    public ValidationMailResponseDTO(Long idCommande, String status, String emailClient, String message) {
        this.idCommande = idCommande;
        this.status = status;
        this.emailClient = emailClient;
        this.message = message;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}