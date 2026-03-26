package mg.votretp.restapi.dto;

import java.util.List;

public class PasserCommandeDTO {

    private String emailClient;
    private String numClient;
    private Long idModePaiement;
    private List<CommandeItemDTO> items;

    public PasserCommandeDTO() {
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

    public List<CommandeItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CommandeItemDTO> items) {
        this.items = items;
    }
}