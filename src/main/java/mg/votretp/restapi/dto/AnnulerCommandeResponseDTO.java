package mg.votretp.restapi.dto;

public class AnnulerCommandeResponseDTO {

    private Long idCommande;
    private String status;
    private String message;

    public AnnulerCommandeResponseDTO() {
    }

    public AnnulerCommandeResponseDTO(Long idCommande, String status, String message) {
        this.idCommande = idCommande;
        this.status = status;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}