package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.StudentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface GroupRepository extends JpaRepository<StudentsGroup, Long> {

    @Query("SELECT COUNT(sg.id) FROM StudentsGroup sg WHERE CONCAT(sg.name, sg.number) LIKE :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT sg FROM StudentsGroup sg WHERE CONCAT(sg.name, sg.number) LIKE :name")
    Optional<StudentsGroup> findOneByName(@Param("name") String name);

    @Query("SELECT sg FROM StudentsGroup sg INNER JOIN CabinetStudent cs ON sg.id = cs.groupStudentId INNER JOIN User u ON u.cabinetStudentId = cs.id WHERE u.id = :userId")
    Optional<StudentsGroup> findOneByUserId(@Param("userId") Long userId);

//    List<SGroup> findAllByGroupStatus(GroupStatus groupStatus);
}
