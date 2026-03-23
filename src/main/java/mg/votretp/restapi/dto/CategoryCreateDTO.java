package mg.votretp.restapi.dto;

public class CategoryCreateDTO {

    private String nom;

    public CategoryCreateDTO() {
    }

    public CategoryCreateDTO(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}