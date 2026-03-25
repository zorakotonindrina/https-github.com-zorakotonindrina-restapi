package mg.votretp.restapi.dto;


public class ValiderCommandeDTO {

    private String emailClient;
    private String numClient;
    private Long idModePaiement;

    public ValiderCommandeDTO() {
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public Long getIdModePaiement() {
        return idModePaiement;
    }

    public void setIdModePaiement(Long idModePaiement) {
        this.idModePaiement = idModePaiement;
    }
}