package com.vitmvit.smarteducation.specification;

import com.vitmvit.smarteducation.config.constants.RoleEnum;
import com.vitmvit.smarteducation.model.entity.Role;
import com.vitmvit.smarteducation.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

/**
 * @see "https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl"
 */
public class UserSpecification {

    public static Specification<User> findAllRoots() {
        return findAllUsers(RoleEnum.ROOT);
    }

    public static Specification<User> findAllAdmins() {
        return findAllUsers(RoleEnum.ADMIN);
    }

    public static Specification<User> findAllSupervisors() {
        return findAllUsers(RoleEnum.SUPERVISOR);
    }

    public static Specification<User> findAllTeachers() {
        return findAllUsers(RoleEnum.TEACHER);
    }

    public static Specification<User> findAllStudents() {
        return findAllUsers(RoleEnum.STUDENT);
    }

    public static Specification<User> findAllUsers() {
        return findAllUsers(RoleEnum.USER);
    }

    public static Specification<User> findAllUsers(RoleEnum roleEnum) {
        return (user, criteriaQuery, criteriaBuilder) -> {
            Join<User, Role> join = user.join("roleList");
            return criteriaBuilder.equal(join.get("name"), roleEnum.getName());
        };
    }
}
