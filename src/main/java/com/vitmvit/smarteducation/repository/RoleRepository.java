package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
