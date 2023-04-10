package com.zadaca.events.repository;

import java.util.Optional;

import com.zadaca.events.enums.ERole;
import com.zadaca.events.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
