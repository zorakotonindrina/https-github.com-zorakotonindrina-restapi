package mg.votretp.restapi.dto;


import java.math.BigDecimal;

public class PlatDetailDTO {

    private Long idPlat;
    private String nom;
    private String image;
    private String detail;
    private Boolean dispo;
    private BigDecimal prixActuel;

    public PlatDetailDTO() {
    }

    public PlatDetailDTO(Long idPlat, String nom, String image, String detail, Boolean dispo, BigDecimal prixActuel) {
        this.idPlat = idPlat;
        this.nom = nom;
        this.image = image;
        this.detail = detail;
        this.dispo = dispo;
        this.prixActuel = prixActuel;
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getDispo() {
        return dispo;
    }

    public void setDispo(Boolean dispo) {
        this.dispo = dispo;
    }

    public BigDecimal getPrixActuel() {
        return prixActuel;
    }

    public void setPrixActuel(BigDecimal prixActuel) {
        this.prixActuel = prixActuel;
    }
}