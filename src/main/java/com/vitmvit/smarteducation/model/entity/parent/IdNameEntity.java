package com.vitmvit.smarteducation.model.entity.parent;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class IdNameEntity extends IdEntity {

    private String name;
}
