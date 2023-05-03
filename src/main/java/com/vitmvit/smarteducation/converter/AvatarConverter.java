package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.entity.Avatar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvatarConverter {

    AvatarResponse convert(Avatar source);
}