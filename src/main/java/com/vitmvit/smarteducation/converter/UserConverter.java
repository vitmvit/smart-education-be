package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.converter.uses.DateConverter;
import com.vitmvit.smarteducation.model.dto.response.UserResponse;
import com.vitmvit.smarteducation.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @see "https://mapstruct.org/"
 */
@Mapper(componentModel = "spring", uses = {
        RoleConverter.class,
        DateConverter.class
})
public interface UserConverter {

    @Mapping(target = "roles", source = "roleList")
    UserResponse convert(User source);

    List<UserResponse> convert(List<User> source);
}
