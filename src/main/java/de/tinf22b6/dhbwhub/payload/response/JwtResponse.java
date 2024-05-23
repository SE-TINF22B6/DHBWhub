package de.tinf22b6.dhbwhub.payload.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    @Getter
    @Setter
    private Long accountId;
    @Getter
    @Setter
    private Long userId;
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long accountId, Long userId, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.accountId = accountId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public List<String> getRoles() {
        return roles;
    }

}
