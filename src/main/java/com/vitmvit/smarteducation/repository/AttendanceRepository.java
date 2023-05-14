package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
}
