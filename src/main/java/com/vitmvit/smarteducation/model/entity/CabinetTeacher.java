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
@DiscriminatorValue("t")
public class CabinetTeacher extends Cabinet {

    private String legend;

    @Column(name = "group_teacher_id", insertable = false, updatable = false)
    private Long groupTeacherId;

    @ManyToOne
    @JoinColumn(
            name = "group_teacher_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cabinet_teacher_to_group")
    )
    private StudentsGroup groupTeacher;
}