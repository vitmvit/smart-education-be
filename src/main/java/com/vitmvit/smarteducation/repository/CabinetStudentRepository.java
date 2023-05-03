package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.CabinetStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetStudentRepository extends JpaRepository<CabinetStudent, Long> {
}
