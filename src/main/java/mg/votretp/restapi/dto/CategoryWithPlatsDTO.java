package mg.votretp.restapi.dto;
import java.util.List;

public class CategoryWithPlatsDTO {

    private Long idCategorie;
    private String nomCategorie;
    private List<PlatDetailDTO> plats;

    public CategoryWithPlatsDTO() {
    }

    public CategoryWithPlatsDTO(Long idCategorie, String nomCategorie, List<PlatDetailDTO> plats) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.plats = plats;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public List<PlatDetailDTO> getPlats() {
        return plats;
    }

    public void setPlats(List<PlatDetailDTO> plats) {
        this.plats = plats;
    }
}