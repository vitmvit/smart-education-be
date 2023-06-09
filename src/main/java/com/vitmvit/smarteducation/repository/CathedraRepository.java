package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Cathedra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface CathedraRepository extends JpaRepository<Cathedra, Long> {

    Optional<Cathedra> findOneByName(String name);
}
