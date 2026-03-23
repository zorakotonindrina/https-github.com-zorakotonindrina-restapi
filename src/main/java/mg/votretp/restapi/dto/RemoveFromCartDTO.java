package mg.votretp.restapi.dto;


public class RemoveFromCartDTO {

    private String emailClient;
    private Long idPlat;

    public RemoveFromCartDTO() {
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }
}