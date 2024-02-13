package ua.dp.amovsesyants.wisewallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private Long userId;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @Column(name = "role", nullable = false, updatable = false)
    private String role;
}
