package mg.votretp.restapi.dto;

public class CommandeItemDTO {

    private Long idPlat;
    private Integer quantite;

    public CommandeItemDTO() {
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}