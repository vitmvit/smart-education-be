package com.vitmvit.smarteducation.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvatarResponse {

    private String originalName;
    private String generatedPath;
    private String generatedName;
    private String mimeType;
}
