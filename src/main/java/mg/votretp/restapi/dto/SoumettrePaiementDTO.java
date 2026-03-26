package mg.votretp.restapi.dto;

public class SoumettrePaiementDTO {

    private Long idCommande;
    private String refPaiement;

    public SoumettrePaiementDTO() {
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public String getRefPaiement() {
        return refPaiement;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }
}