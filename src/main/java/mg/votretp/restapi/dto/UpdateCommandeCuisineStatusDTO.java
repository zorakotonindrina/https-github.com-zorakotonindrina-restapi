package mg.votretp.restapi.dto;

public class UpdateCommandeCuisineStatusDTO {

    private Long idCommande;
    private String status;

    public UpdateCommandeCuisineStatusDTO() {
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
}