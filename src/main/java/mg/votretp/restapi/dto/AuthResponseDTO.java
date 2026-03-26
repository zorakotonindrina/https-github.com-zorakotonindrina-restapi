package mg.votretp.restapi.dto;

public class AuthResponseDTO {

    private String accessToken;
    private String refreshToken;
    private UserLoginDTO user;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String accessToken, String refreshToken, UserLoginDTO user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserLoginDTO getUser() {
        return user;
    }

    public void setUser(UserLoginDTO user) {
        this.user = user;
    }
}