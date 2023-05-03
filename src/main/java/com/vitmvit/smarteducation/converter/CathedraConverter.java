package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.model.dto.request.FacultyRequest;
import com.vitmvit.smarteducation.model.dto.response.FacultyResponse;
import com.vitmvit.smarteducation.model.entity.Cathedra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CathedraConverter {

    FacultyResponse convert(Cathedra source);

    Cathedra convert(FacultyRequest source);

    List<FacultyResponse> convert(List<Cathedra> source);
}
