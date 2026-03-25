package mg.votretp.restapi.dto;

import org.springframework.hateoas.RepresentationModel;

public class CommandeConfirmeeResponseDTO extends RepresentationModel<CommandeConfirmeeResponseDTO> {

    private Long idCommande;
    private Integer numCommande;
    private String status;
    private String emailClient;
    private String numClient;
    private String confirmePar;
    private String message;

    public CommandeConfirmeeResponseDTO() {
    }

    public CommandeConfirmeeResponseDTO(Long idCommande, Integer numCommande, String status,
                                        String emailClient, String numClient,
                                        String confirmePar, String message) {
        this.idCommande = idCommande;
        this.numCommande = numCommande;
        this.status = status;
        this.emailClient = emailClient;
        this.numClient = numClient;
        this.confirmePar = confirmePar;
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

    public String getConfirmePar() {
        return confirmePar;
    }

    public void setConfirmePar(String confirmePar) {
        this.confirmePar = confirmePar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}