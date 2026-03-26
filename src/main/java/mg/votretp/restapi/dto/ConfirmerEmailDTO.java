package mg.votretp.restapi.dto;

public class ConfirmerEmailDTO {

    private Long idCommande;
    private String code;

    public ConfirmerEmailDTO() {
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}