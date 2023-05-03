package com.vitmvit.smarteducation.model.entity.parent;

import com.vitmvit.smarteducation.model.entity.Cathedra;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @see "https://habr.com/ru/post/337488/"
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class Cabinet extends IdEntity {

    @ManyToOne
    private Cathedra cathedra;
}
