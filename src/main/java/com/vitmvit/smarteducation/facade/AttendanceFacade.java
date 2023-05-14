package com.vitmvit.smarteducation.facade;

import com.vitmvit.smarteducation.model.dto.page.PageContentResponse;
import com.vitmvit.smarteducation.model.dto.request.AttendanceGroupRequest;
import com.vitmvit.smarteducation.model.dto.request.AttendanceRequest;
import com.vitmvit.smarteducation.model.dto.request.AttendanceSearch;
import com.vitmvit.smarteducation.model.dto.response.AttendanceResponse;

import java.util.List;

public interface AttendanceFacade {

    AttendanceResponse findOne(Long id);

    PageContentResponse<AttendanceResponse> findAll(int pageNumber, String sortField, AttendanceSearch dto);

    AttendanceResponse save(List<AttendanceRequest> list);

    AttendanceResponse save(AttendanceGroupRequest dto);

    void remove(Long id);
}
