package mg.votretp.restapi.dto;

public class CommandeAdminLigneDTO {

    private String nomPlat;
    private Integer quantite;
    private Double prixUnitaire;

    public CommandeAdminLigneDTO() {}

    public CommandeAdminLigneDTO(String nomPlat, Integer quantite, Double prixUnitaire) {
        this.nomPlat = nomPlat;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
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

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    // getters/setters
}