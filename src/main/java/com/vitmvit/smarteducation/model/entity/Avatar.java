package com.vitmvit.smarteducation.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "attachment")
public class Avatar {


    private String originalName;
    private String generatedPath;

    @Id
    private String generatedName;

    private String mimeType;
}
