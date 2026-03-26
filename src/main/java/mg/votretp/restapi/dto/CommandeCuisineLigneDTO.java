package mg.votretp.restapi.dto;

public class CommandeCuisineLigneDTO {

    private Long idPlat;
    private String nomPlat;
    private Integer quantite;

    public CommandeCuisineLigneDTO() {
    }

    public CommandeCuisineLigneDTO(Long idPlat, String nomPlat, Integer quantite) {
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
        this.quantite = quantite;
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}