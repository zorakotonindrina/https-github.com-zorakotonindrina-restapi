package mg.votretp.restapi.dto;


public class SoumettrePaiementDTO {

    private String emailClient;
    private String refPaiement;

    public SoumettrePaiementDTO() {
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getRefPaiement() {
        return refPaiement;
    }

    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }
}