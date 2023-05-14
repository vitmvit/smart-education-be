package com.vitmvit.smarteducation.model.dto.request;

import com.vitmvit.smarteducation.model.constant.GroupStatus;
import com.vitmvit.smarteducation.model.dto.parent.IdNameDto;
import com.vitmvit.smarteducation.validate.GroupExists;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@GroupExists
public class GroupRequest extends IdNameDto {

    private Integer number;
    private LocalDate admissionDate;
    private LocalDate graduationDate;
    private GroupStatus groupStatus;
//    private String groupStatus;
}
