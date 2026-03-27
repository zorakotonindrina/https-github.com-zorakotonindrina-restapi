package mg.votretp.restapi.dto;

import java.time.LocalDate;

public class PeriodeStatDTO {

    private LocalDate dateDebut;
    private LocalDate dateFin;

    public PeriodeStatDTO() {
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}