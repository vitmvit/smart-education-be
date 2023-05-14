package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.dto.response.Base64Response;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    Base64Response getBase64ByUuid(String uuid);

    AvatarResponse getDtoByUuid(String uuid);

    AvatarResponse save(MultipartFile multipartFile);

    void removeByUuid(String uuid);
}
