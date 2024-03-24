package ua.dp.amovsesyants.wisewallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 64, nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @OneToMany
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
