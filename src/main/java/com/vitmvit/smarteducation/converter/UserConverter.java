package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.converter.uses.DateConverter;
import com.vitmvit.smarteducation.model.dto.response.UserResponse;
import com.vitmvit.smarteducation.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateConverter.class})
public interface UserConverter {

    UserResponse convert(User source);
}
