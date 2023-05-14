package com.vitmvit.smarteducation.model.dto.response;

import com.vitmvit.smarteducation.model.dto.parent.IdNameLogDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse extends IdNameLogDto {

    private Integer number;
    private String admissionDate;
    private String graduationDate;
    //private GroupStatus groupStatus;
    private String groupStatus;
}
