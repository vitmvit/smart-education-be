package com.vitmvit.smarteducation.facade.impl;

import com.vitmvit.smarteducation.config.constants.RoleEnum;
import com.vitmvit.smarteducation.converter.AttendanceConverter;
import com.vitmvit.smarteducation.facade.AttendanceFacade;
import com.vitmvit.smarteducation.model.dto.page.PageContentResponse;
import com.vitmvit.smarteducation.model.dto.page.PageResponse;
import com.vitmvit.smarteducation.model.dto.request.AttendanceGroupRequest;
import com.vitmvit.smarteducation.model.dto.request.AttendanceRequest;
import com.vitmvit.smarteducation.model.dto.request.AttendanceSearch;
import com.vitmvit.smarteducation.model.dto.response.AttendanceResponse;
import com.vitmvit.smarteducation.model.entity.Attendance;
import com.vitmvit.smarteducation.model.entity.AttendanceGroup;
import com.vitmvit.smarteducation.model.entity.User;
import com.vitmvit.smarteducation.service.AttendanceGroupService;
import com.vitmvit.smarteducation.service.AttendanceService;
import com.vitmvit.smarteducation.service.UserService;
import com.vitmvit.smarteducation.specification.AttendanceSpecification;
import com.vitmvit.smarteducation.util.IdUtils;
import com.vitmvit.smarteducation.util.PageableUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AttendanceFacadeImpl implements AttendanceFacade {

    private final AttendanceService attendanceService;
    private final AttendanceGroupService attendanceGroupService;
    private final UserService userService;
    private final AttendanceConverter attendanceConverter;

    @Override
    public AttendanceResponse findOne(Long id) {
        return attendanceConverter.convert(attendanceService.findOne(id));
    }

    @Override
    public PageContentResponse<AttendanceResponse> findAll(int pageNumber, String sortField, AttendanceSearch dto) {

        // если ни один из параметров поиска не был указан - кидаем исключение
        if (IdUtils.isNotPresent(dto.getSubjectId()) & IdUtils.isNotPresent(dto.getTeacherId()) & IdUtils.isNotPresent(dto.getStudentId())) {
            throw new ValidationException("Bad parameters");
        }

        Page<Attendance> page = null;
        Pageable pageable = PageableUtils.getPageable(pageNumber, false, sortField);

        if (IdUtils.isPresent(dto.getSubjectId()) & IdUtils.isPresent(dto.getTeacherId()) & IdUtils.isPresent(dto.getStudentId())) {
            // по всем параметрам
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllBySubjectAndTeacherAndStudent(dto)), pageable);
        } else if (IdUtils.isPresent(dto.getSubjectId()) & IdUtils.isPresent(dto.getTeacherId()) & IdUtils.isNotPresent(dto.getStudentId())) {
            // по subjectId/teacherId
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllBySubjectAndTeacher(dto)), pageable);
        } else if (IdUtils.isPresent(dto.getSubjectId()) & IdUtils.isNotPresent(dto.getTeacherId()) & IdUtils.isPresent(dto.getStudentId())) {
            // по subjectId/studentId
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllBySubjectAndStudent(dto)), pageable);
        } else if (IdUtils.isNotPresent(dto.getSubjectId()) & IdUtils.isPresent(dto.getTeacherId()) & IdUtils.isPresent(dto.getStudentId())) {
            // по teacherId/studentId
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllByTeacherAndStudent(dto)), pageable);
        } else if (IdUtils.isPresent(dto.getSubjectId()) & IdUtils.isNotPresent(dto.getTeacherId()) & IdUtils.isNotPresent(dto.getStudentId())) {
            // по subjectId
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllBySubject(dto)), pageable);
        } else if (IdUtils.isNotPresent(dto.getSubjectId()) & IdUtils.isPresent(dto.getTeacherId()) & IdUtils.isNotPresent(dto.getStudentId())) {
            // по teacherId
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllByTeacher(dto)), pageable);
        } else if (IdUtils.isNotPresent(dto.getSubjectId()) & IdUtils.isNotPresent(dto.getTeacherId()) & IdUtils.isPresent(dto.getStudentId())) {
            // по studentId
            page = attendanceService.findAll(Specification.where(AttendanceSpecification.findAllByStudent(dto)), pageable);
        }
        if (page == null) {
            // на случай, если что-то пошло не так, кидаем заглушку, null возвращать нельзя
            return new PageContentResponse<>(new PageResponse(), Collections.emptyList());
        }
        return new PageContentResponse<>(
                new PageResponse(
                        page.getTotalElements(),
                        page.getTotalPages(),
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getOffset(),
                        pageable.hasPrevious()
                ),
                page.getContent().isEmpty()
                        ? Collections.emptyList()
                        : page.getContent().stream().map(attendanceConverter::convert).collect(Collectors.toList())
        );
    }

    @Override
    public AttendanceResponse save(List<AttendanceRequest> list) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRoleList().size() == 1) {
            if (user.getRoleList().stream().anyMatch(role -> role.getFullName().equals(RoleEnum.STUDENT.getName()))) {
                throw new RuntimeException("Operation prohibited");
            }
        }
        if (list != null && list.size() > 0) {
            Attendance attendance = new Attendance();
            attendance.setDate(list.get(0).getDate());
            attendance.setGroupId(list.get(0).getGroupId());
            attendance.setSubjectId(list.get(0).getSubjectId());
            attendance.setTeacherId(list.get(0).getTeacherId());
            attendance.setLessonNumber(list.get(0).getLessonNumber());
            attendance = attendanceService.save(attendance);
            List<AttendanceGroup> attendanceGroupList = new ArrayList<>(list.size());
            for (AttendanceRequest attendanceRequest : list) {
                AttendanceGroup attendanceGroup = new AttendanceGroup();
                attendanceGroup.setAttendance(attendance);
                attendanceGroup.setStudentId(attendanceGroup.getStudentId());
                attendanceGroup.setVisit(attendanceGroup.getVisit());
                attendanceGroup.setReason(attendanceGroup.getReason());
                attendanceGroup.setDescription(attendanceRequest.getDescription());
            }
            attendance.setAttendanceGroupList(attendanceGroupList);
            return attendanceConverter.convert(attendanceService.save(attendance));
        }
        throw new RuntimeException("AttendanceRequest list is empty");
    }

    @Override
    public AttendanceResponse save(AttendanceGroupRequest dto) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRoleList().size() == 1) {
            if (user.getRoleList().stream().anyMatch(role -> role.getFullName().equals(RoleEnum.STUDENT.getName()))) {
                throw new RuntimeException("Operation prohibited");
            }
        }
        Attendance attendance = attendanceService.findOne(dto.getAttendanceId());
        Optional<AttendanceGroup> attendanceGroupOptional = attendance.getAttendanceGroupList()
                .stream()
                .filter(item -> item.getStudentId().equals(dto.getStudentId()))
                .findFirst();
        if (attendanceGroupOptional.isPresent()) {
            AttendanceGroup attendanceGroup = attendanceGroupOptional.get();
            attendanceGroup.setAttendance(attendance);
            attendanceGroup.setVisit(dto.getVisit());
            attendanceGroup.setReason(dto.getReason());
            attendanceGroup.setDescription(dto.getDescription());
            attendanceGroupService.save(attendanceGroup);
            return attendanceConverter.convert(attendanceService.findOne(attendance.getId()));
        }
        throw new EntityNotFoundException("AttendanceGroup not found by student id: " + dto.getStudentId());
    }

    @Override
    public void remove(Long id) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.getRoleList().size() == 1) {
            if (user.getRoleList().stream().anyMatch(role -> role.getFullName().equals(RoleEnum.STUDENT.getName()))) {
                throw new RuntimeException("Operation prohibited");
            }
        }
        attendanceService.remove(id);
    }
}
