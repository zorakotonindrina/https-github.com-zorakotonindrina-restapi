package mg.votretp.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tables")
public class TableRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTable;

    private String numero; // ex: "T1", "T2"

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    public TableRestaurant() {
    }

    public Long getIdTable() {
        return idTable;
    }

    public void setIdTable(Long idTable) {
        this.idTable = idTable;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}