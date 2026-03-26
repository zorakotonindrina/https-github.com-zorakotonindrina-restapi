package mg.votretp.restapi.dto;

public class CommandeJourStatDTO {

    private Long enAttente;
    private Long enPreparation;
    private Long finie;

    public CommandeJourStatDTO() {
    }

    public CommandeJourStatDTO(Long enAttente, Long enPreparation, Long finie) {
        this.enAttente = enAttente;
        this.enPreparation = enPreparation;
        this.finie = finie;
    }

    public Long getEnAttente() {
        return enAttente;
    }

    public void setEnAttente(Long enAttente) {
        this.enAttente = enAttente;
    }

    public Long getEnPreparation() {
        return enPreparation;
    }

    public void setEnPreparation(Long enPreparation) {
        this.enPreparation = enPreparation;
    }

    public Long getFinie() {
        return finie;
    }

    public void setFinie(Long finie) {
        this.finie = finie;
    }
}