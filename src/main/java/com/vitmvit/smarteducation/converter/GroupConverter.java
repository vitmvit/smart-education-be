package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.converter.uses.DateConverter;
import com.vitmvit.smarteducation.model.dto.request.GroupRequest;
import com.vitmvit.smarteducation.model.dto.response.GroupResponse;
import com.vitmvit.smarteducation.model.entity.SGroup;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateConverter.class})
public interface GroupConverter {

    GroupResponse convert(SGroup source);

    SGroup convert(GroupRequest source);

    List<GroupResponse> convert(List<SGroup> source);
}
