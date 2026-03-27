package mg.votretp.restapi.dto;

import java.math.BigDecimal;

public class MontantParModePaiementDTO {

    private String modePaiement;
    private BigDecimal montantTotal;

    public MontantParModePaiementDTO() {
    }

    public MontantParModePaiementDTO(String modePaiement, BigDecimal montantTotal) {
        this.modePaiement = modePaiement;
        this.montantTotal = montantTotal;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }
}