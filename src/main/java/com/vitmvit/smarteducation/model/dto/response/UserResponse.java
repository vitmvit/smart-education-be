package com.vitmvit.smarteducation.model.dto.response;

import com.vitmvit.smarteducation.model.dto.parent.IdNameLogDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse extends IdNameLogDto {

    private String login;
    private List<RoleResponse> roles;
    private String avatarUuid;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private Integer studentNumber;
    private Integer active;
    private String description;
}
