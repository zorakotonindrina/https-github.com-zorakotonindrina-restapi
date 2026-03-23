package mg.votretp.restapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prix_plats")
public class PrixPlat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprix")
    private Long idPrix;

    @Column(name = "date_prix")
    private LocalDateTime datePrix;

    @Column(name = "prix", nullable = false)
    private BigDecimal prix;

    @ManyToOne
    @JoinColumn(name = "id_plat")
    private Plat plat;

    @PrePersist
    public void prePersist() {
        this.datePrix = LocalDateTime.now();
    }

    public PrixPlat() {
    }

    public Long getIdPrix() {
        return idPrix;
    }

    public void setIdPrix(Long idPrix) {
        this.idPrix = idPrix;
    }

    public LocalDateTime getDatePrix() {
        return datePrix;
    }

    public void setDatePrix(LocalDateTime datePrix) {
        this.datePrix = datePrix;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }
}