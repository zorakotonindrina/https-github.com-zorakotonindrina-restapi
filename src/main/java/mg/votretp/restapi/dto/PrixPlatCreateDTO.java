package mg.votretp.restapi.dto;

import java.math.BigDecimal;

public class PrixPlatCreateDTO {

    private BigDecimal prix;
    private Long idPlat;

    public PrixPlatCreateDTO() {
    }

    public PrixPlatCreateDTO(BigDecimal prix, Long idPlat) {
        this.prix = prix;
        this.idPlat = idPlat;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }
}