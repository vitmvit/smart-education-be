package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.converter.uses.DateConverter;
import com.vitmvit.smarteducation.model.dto.request.SubjectRequest;
import com.vitmvit.smarteducation.model.dto.response.SubjectResponse;
import com.vitmvit.smarteducation.model.entity.Subject;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DateConverter.class})
public interface SubjectConverter {

    SubjectResponse convert(Subject source);

    Subject convert(SubjectRequest source);

    List<SubjectResponse> convert(List<Subject> source);
}
