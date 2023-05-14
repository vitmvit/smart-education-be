package com.vitmvit.smarteducation.specification;

import com.vitmvit.smarteducation.model.dto.request.AttendanceSearch;
import com.vitmvit.smarteducation.model.entity.Attendance;
import org.springframework.data.jpa.domain.Specification;

/**
 * @see "https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl"
 */
public class AttendanceSpecification {

    public static Specification<Attendance> findAllBySubjectAndTeacherAndStudent(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("subjectId"), dto.getSubjectId()),
                    criteriaBuilder.equal(attendance.get("teacherId"), dto.getTeacherId()),
                    criteriaBuilder.equal(attendance.get("studentId"), dto.getStudentId())
            );
        };
    }

    public static Specification<Attendance> findAllBySubjectAndTeacher(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("subjectId"), dto.getSubjectId()),
                    criteriaBuilder.equal(attendance.get("teacherId"), dto.getTeacherId())
            );
        };
    }

    public static Specification<Attendance> findAllBySubjectAndStudent(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("subjectId"), dto.getSubjectId()),
                    criteriaBuilder.equal(attendance.get("studentId"), dto.getStudentId())
            );
        };
    }

    public static Specification<Attendance> findAllByTeacherAndStudent(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("teacherId"), dto.getTeacherId()),
                    criteriaBuilder.equal(attendance.get("studentId"), dto.getStudentId())
            );
        };
    }

    public static Specification<Attendance> findAllBySubject(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("subjectId"), dto.getSubjectId())
            );
        };
    }

    public static Specification<Attendance> findAllByTeacher(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("teacherId"), dto.getTeacherId())
            );
        };
    }

    public static Specification<Attendance> findAllByStudent(AttendanceSearch dto) {
        return (attendance, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.between(attendance.get("date"), dto.getFrom(), dto.getTo()),
                    criteriaBuilder.equal(attendance.get("groupId"), dto.getGroupId()),
                    criteriaBuilder.equal(attendance.get("studentId"), dto.getStudentId())
            );
        };
    }
}
