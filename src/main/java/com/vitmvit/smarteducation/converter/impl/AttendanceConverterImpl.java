package com.vitmvit.smarteducation.converter.impl;

import com.vitmvit.smarteducation.converter.AttendanceConverter;
import com.vitmvit.smarteducation.converter.UserConverter;
import com.vitmvit.smarteducation.model.dto.response.AttendanceGroupResponse;
import com.vitmvit.smarteducation.model.dto.response.AttendanceResponse;
import com.vitmvit.smarteducation.model.entity.Attendance;
import com.vitmvit.smarteducation.model.entity.AttendanceGroup;
import com.vitmvit.smarteducation.service.GroupService;
import com.vitmvit.smarteducation.service.SubjectService;
import com.vitmvit.smarteducation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AttendanceConverterImpl implements AttendanceConverter {

    private final GroupService groupService;
    private final SubjectService subjectService;
    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    public AttendanceResponse convert(Attendance source) {
        if (source == null) {
            return null;
        }
        AttendanceResponse target = new AttendanceResponse();
        target.setDate(source.getDate());
        target.setGroup(groupService.findOne(source.getGroupId()));
        target.setSubject(subjectService.findOne(source.getSubjectId()));
        target.setTeacher(userConverter.convert(userService.findById(source.getTeacherId())));
        target.setLessonNumber(source.getLessonNumber());
        return target;
    }

    @Override
    public AttendanceGroupResponse convert(AttendanceGroup source) {
        if (source == null) {
            return null;
        }
        AttendanceGroupResponse target = new AttendanceGroupResponse();
        target.setStudent(userConverter.convert(userService.findById(source.getStudentId())));
        target.setVisit(source.getVisit());
        target.setReason(source.getReason());
        target.setDescription(source.getDescription());
        return target;
    }
}
