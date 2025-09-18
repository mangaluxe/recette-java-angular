package org.example.backspringboot.dto;

public class LoginResponse {

    // ========== Propriétés ==========

    private String token;
    private Long userId;
    private String username;
    private String roleName;


    // ========== Constructeurs ==========

    public LoginResponse() { }

    public LoginResponse(String token, Long userId, String username, String roleName) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.roleName = roleName;
    }


    // ========== Getters/Setters ==========

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String role) { this.roleName = roleName; }

}