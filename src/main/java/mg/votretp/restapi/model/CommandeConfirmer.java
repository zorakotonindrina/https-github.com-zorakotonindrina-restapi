package mg.votretp.restapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commande_confirmer")
public class CommandeConfirmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconfirmer")
    private Long idConfirmer;

    @Column(name = "date_confirmer")
    private LocalDateTime dateConfirmer;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

    @PrePersist
    public void prePersist() {
        this.dateConfirmer = LocalDateTime.now();
    }

    public Long getIdConfirmer() {
        return idConfirmer;
    }

    public void setIdConfirmer(Long idConfirmer) {
        this.idConfirmer = idConfirmer;
    }

    public LocalDateTime getDateConfirmer() {
        return dateConfirmer;
    }

    public void setDateConfirmer(LocalDateTime dateConfirmer) {
        this.dateConfirmer = dateConfirmer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}