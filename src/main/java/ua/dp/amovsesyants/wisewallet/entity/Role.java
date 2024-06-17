package ua.dp.amovsesyants.wisewallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.dp.amovsesyants.wisewallet.enums.RoleName;

@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, updatable = false, unique = true)
    private RoleName name;

    public Role(final RoleName name) {
        this.name = name;
    }
}
