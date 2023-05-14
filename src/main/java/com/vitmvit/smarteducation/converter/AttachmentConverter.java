package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.entity.Avatar;
import org.mapstruct.Mapper;

/**
 * @see "https://mapstruct.org/"
 */
@Mapper(componentModel = "spring")
public interface AttachmentConverter {

    AvatarResponse convert(Avatar source);
}