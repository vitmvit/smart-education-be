package com.vitmvit.smarteducation.model.dto.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class IdNameLogDto extends IdNameDto {

    private String dateCreation;
    private String lastModified;
    private Long version;
}
