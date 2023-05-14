package com.vitmvit.smarteducation.model.entity;

import com.vitmvit.smarteducation.model.entity.parent.IdNameLogEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "students_group")
public class SGroup extends IdNameLogEntity {

    private Integer number;
    private LocalDate admissionDate;
    private LocalDate graduationDate;

//    @Enumerated(EnumType.STRING)
//    private GroupStatus groupStatus;

    private String groupStatus;

    public String getGroupNumber() {
        return getName() + getNumber();
    }
}
