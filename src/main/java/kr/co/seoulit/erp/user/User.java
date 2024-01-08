package kr.co.seoulit.erp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    private String provider;
    private String providerId;


    @Builder
    public User(String username, String password, String email, String role, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User() {

    }
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
