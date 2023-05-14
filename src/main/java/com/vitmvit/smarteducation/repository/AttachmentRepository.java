package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface AttachmentRepository extends JpaRepository<Avatar, String> {

    Optional<Avatar> findByGeneratedName(String uuid);

    void deleteByGeneratedName(String uuid);
}
