package com.vitmvit.smarteducation.model.entity;

import com.vitmvit.smarteducation.model.entity.parent.IdLogEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AttendanceGroup extends IdLogEntity {

    @Column(name = "attendance_id", insertable = false, updatable = false)
    private Long attendanceId;

    private Long studentId;
    private Boolean visit;
    private Integer reason;
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "attendance_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_attendance_group_to_attendance_id")
    )
    private Attendance attendance;
}
