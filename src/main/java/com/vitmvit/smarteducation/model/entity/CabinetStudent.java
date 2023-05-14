package com.vitmvit.smarteducation.model.entity;

import com.vitmvit.smarteducation.model.entity.parent.Cabinet;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @see "https://habr.com/ru/post/337488/"
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("s")
public class CabinetStudent extends Cabinet {

    @Column(name = "group_student_id", insertable = false, updatable = false)
    private Long groupStudentId;

    //------------------------------------------------------------------------------------------------------------------

    @ManyToOne
    @JoinColumn(
            name = "group_student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cabinet_student_to_group")
    )
    private StudentsGroup groupStudent;
}