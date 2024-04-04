package de.tinf22b6.dhbwhub.repository;

import de.tinf22b6.dhbwhub.model.ERole;
import de.tinf22b6.dhbwhub.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
