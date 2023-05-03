package com.vitmvit.smarteducation.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Settings {

    @Id
    @Column(name = "k", nullable = false, unique = true)
    private String key;

    @Column(name = "v", nullable = false)
    private String value;
}
