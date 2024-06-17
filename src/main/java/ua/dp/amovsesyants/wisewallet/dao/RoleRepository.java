package ua.dp.amovsesyants.wisewallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dp.amovsesyants.wisewallet.entity.Role;
import ua.dp.amovsesyants.wisewallet.enums.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);

}
