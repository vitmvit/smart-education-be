package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.AttendanceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface AttendanceGroupRepository extends JpaRepository<AttendanceGroup, Long> {
}
