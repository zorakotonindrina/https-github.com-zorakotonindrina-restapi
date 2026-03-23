package mg.votretp.restapi.dto;

public class PlatCreateDTO {

    private String nom;
    private String image;
    private String detail;
    private Boolean dispo;
    private Long idCategorie;
    private Long idRestaurant;

    public PlatCreateDTO() {
    }

    public PlatCreateDTO(String nom, String image, String detail, Boolean dispo, Long idCategorie, Long idRestaurant) {
        this.nom = nom;
        this.image = image;
        this.detail = detail;
        this.dispo = dispo;
        this.idCategorie = idCategorie;
        this.idRestaurant = idRestaurant;
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

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}