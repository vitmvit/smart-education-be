package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findOneByName(String name);
}
