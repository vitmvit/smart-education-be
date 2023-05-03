package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.model.dto.response.RoleResponse;
import com.vitmvit.smarteducation.model.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleConverter {

    RoleResponse convert(Role source);

    List<RoleResponse> convert(List<Role> source);
}
