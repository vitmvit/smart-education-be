package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.converter.uses.DateConverter;
import com.vitmvit.smarteducation.converter.uses.GroupStatusConverter;
import com.vitmvit.smarteducation.model.dto.request.GroupRequest;
import com.vitmvit.smarteducation.model.dto.response.GroupResponse;
import com.vitmvit.smarteducation.model.entity.StudentsGroup;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @see "https://mapstruct.org/"
 */
@Mapper(componentModel = "spring", uses = {
        GroupStatusConverter.class,
        DateConverter.class
})
public interface GroupConverter {

    GroupResponse convert(StudentsGroup source);

    StudentsGroup convert(GroupRequest source);

    List<GroupResponse> convert(List<StudentsGroup> source);
}
