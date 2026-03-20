package mg.votretp.restapi.dto;

public class LoginDTO {

    private String numero;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String numero, String password) {
        this.numero = numero;
        this.password = password;
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
}