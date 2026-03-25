package mg.votretp.restapi.dto;


public class ConfirmerEmailDTO {

    private String emailClient;
    private String code;

    public ConfirmerEmailDTO() {
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}