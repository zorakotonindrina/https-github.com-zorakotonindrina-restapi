package mg.votretp.restapi.dto;

public class TopPlatDTO {

    private Long idPlat;
    private String nomPlat;
    private Long quantiteTotale;

    public TopPlatDTO() {
    }

    public TopPlatDTO(Long idPlat, String nomPlat, Long quantiteTotale) {
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
        this.quantiteTotale = quantiteTotale;
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

    public Long getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(Long quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }
}