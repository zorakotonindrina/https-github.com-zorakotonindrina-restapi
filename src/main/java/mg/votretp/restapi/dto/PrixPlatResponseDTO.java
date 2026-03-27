package mg.votretp.restapi.dto;

import java.time.LocalDateTime;

public class PrixPlatResponseDTO {

    private Long idPrixPlat;
    private Double prix;
    private LocalDateTime datePrix;
    private Long idPlat;
    private String nomPlat;

    public PrixPlatResponseDTO() {
    }

    public PrixPlatResponseDTO(Long idPrixPlat, Double prix, LocalDateTime datePrix, Long idPlat, String nomPlat) {
        this.idPrixPlat = idPrixPlat;
        this.prix = prix;
        this.datePrix = datePrix;
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
    }

    public Long getIdPrixPlat() {
        return idPrixPlat;
    }

    public Double getPrix() {
        return prix;
    }

    public LocalDateTime getDatePrix() {
        return datePrix;
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public String getNomPlat() {
        return nomPlat;
    }
}