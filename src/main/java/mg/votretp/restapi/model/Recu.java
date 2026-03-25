package mg.votretp.restapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "recu")
public class Recu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecu")
    private Long idRecu;

    @Column(name = "ref_payament")
    private String refPayament;

    @Column(name = "prix_total")
    private BigDecimal prixTotal;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

    public Recu() {
    }

    public Long getIdRecu() {
        return idRecu;
    }

    public void setIdRecu(Long idRecu) {
        this.idRecu = idRecu;
    }

    public String getRefPayament() {
        return refPayament;
    }

    public void setRefPayament(String refPayament) {
        this.refPayament = refPayament;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}