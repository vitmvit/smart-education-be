package com.vitmvit.smarteducation.model.dto.response;

import com.vitmvit.smarteducation.model.dto.parent.IdNameLogDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends IdNameLogDto {

    private String lastName;
    private String middleName;
    private String phoneNumber;
    private Integer studentNumber;
}
