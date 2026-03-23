package mg.votretp.restapi.dto;


import java.math.BigDecimal;
import java.util.List;

public class PanierResponseDTO {

    private Long idCommande;
    private String emailClient;
    private String numClient;
    private String status;
    private List<PanierLigneDTO> lignes;
    private BigDecimal totalGeneral;

    public PanierResponseDTO() {
    }

    public PanierResponseDTO(Long idCommande, String emailClient, String numClient,
                             String status, List<PanierLigneDTO> lignes, BigDecimal totalGeneral) {
        this.idCommande = idCommande;
        this.emailClient = emailClient;
        this.numClient = numClient;
        this.status = status;
        this.lignes = lignes;
        this.totalGeneral = totalGeneral;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PanierLigneDTO> getLignes() {
        return lignes;
    }

    public void setLignes(List<PanierLigneDTO> lignes) {
        this.lignes = lignes;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }
}