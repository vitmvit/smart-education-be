package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @see "https://docs.spring.io/spring-data/jpa/docs/current/reference/html/"
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u INNER JOIN CabinetStudent cs ON cs.id = u.cabinetStudentId INNER JOIN StudentsGroup sg ON sg.id = cs.groupStudentId WHERE u.id = :id")
    List<User> findAllInGroupByUserId(Long id);

    @Query("SELECT u FROM User u INNER JOIN CabinetStudent cs ON cs.id = u.cabinetStudentId INNER JOIN StudentsGroup sg ON sg.id = cs.groupStudentId WHERE sg.id = :groupId")
    List<User> findAllByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT u FROM User u INNER JOIN CabinetStudent cs ON cs.id = u.cabinetStudentId INNER JOIN StudentsGroup sg ON sg.id = cs.groupStudentId WHERE CONCAT(sg.name, sg.number) LIKE :groupName")
    List<User> findAllByGroupName(@Param("groupName") String groupName);

    @Query("SELECT u FROM User u INNER JOIN CabinetTeacher ct ON ct.id = u.id")
    List<User> findAllTeachers();

//    List<User> findAllTeachersBySubjectId();
}
