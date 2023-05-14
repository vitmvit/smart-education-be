package com.vitmvit.smarteducation.model.entity;

import com.vitmvit.smarteducation.model.entity.parent.IdLogEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Attendance extends IdLogEntity {

    private LocalDate date;
    private Long groupId;
    private Long subjectId;
    private Long teacherId;
    private Integer lessonNumber;

    @OneToMany(mappedBy = "attendance")
    private List<AttendanceGroup> attendanceGroupList;
}
