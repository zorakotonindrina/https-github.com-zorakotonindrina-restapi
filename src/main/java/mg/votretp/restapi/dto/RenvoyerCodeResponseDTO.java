package mg.votretp.restapi.dto;

public class RenvoyerCodeResponseDTO {

    private Long idCommande;
    private String message;
    private String codeValidation;

    public RenvoyerCodeResponseDTO() {
    }

    public RenvoyerCodeResponseDTO(Long idCommande, String message, String codeValidation) {
        this.idCommande = idCommande;
        this.message = message;
        this.codeValidation = codeValidation;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
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