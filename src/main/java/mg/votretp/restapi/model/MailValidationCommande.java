package mg.votretp.restapi.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mail_validation_commande")
public class MailValidationCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvalidation")
    private Long idValidation;

    @Column(name = "email_client")
    private String emailClient;

    @Column(name = "code")
    private String code;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "expire_at")
    private LocalDateTime expireAt;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    private Commande commande;

    @PrePersist
    public void prePersist() {
        this.dateCreation = LocalDateTime.now();
    }

    public Long getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(Long idValidation) {
        this.idValidation = idValidation;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}