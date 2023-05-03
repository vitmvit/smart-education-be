package com.vitmvit.smarteducation.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Base64Response {

    private String originalName;
    private String description;
    private String base64 = "";
    private String mimeType;
}
