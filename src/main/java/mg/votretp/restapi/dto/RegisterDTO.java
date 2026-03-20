package mg.votretp.restapi.dto;

public class RegisterDTO {

    private String nom;
    private String prenom;
    private String numero;
    private String password;
    private String roleName;

    public RegisterDTO() {
    }

    public RegisterDTO(String nom, String prenom, String numero, String password, String roleName) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.password = password;
        this.roleName = roleName;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}