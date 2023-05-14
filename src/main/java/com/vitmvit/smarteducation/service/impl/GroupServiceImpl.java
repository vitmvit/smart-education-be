package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.converter.GroupConverter;
import com.vitmvit.smarteducation.converter.uses.GroupStatusConverter;
import com.vitmvit.smarteducation.model.dto.request.GroupRequest;
import com.vitmvit.smarteducation.model.dto.response.GroupResponse;
import com.vitmvit.smarteducation.model.entity.StudentsGroup;
import com.vitmvit.smarteducation.repository.GroupRepository;
import com.vitmvit.smarteducation.service.GroupService;
import com.vitmvit.smarteducation.util.IdUtils;
import com.vitmvit.smarteducation.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;

@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupConverter groupConverter;

    @Override
    public boolean exists(String name) {
        return groupRepository.existsByName(name);
    }

    @Override
    public GroupResponse findOne(Long id) {
        return findOne(id, null, null);
    }

    @Override
    public GroupResponse findOne(String name) {
        return findOne(null, name, null);
    }

    @Override
    public GroupResponse findOne(Long id, String name, Long userId) {
        if (IdUtils.isPresent(id)) {
            return groupConverter.convert(
                    groupRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("Student's group not found by id: " + id)
                    )
            );
        } else if (StringUtils.isNotEmpty(name)) {
            return groupConverter.convert(
                    groupRepository.findOneByName(name).orElseThrow(
                            () -> new EntityNotFoundException("Student's group not found by name: " + name)
                    )
            );
        } else if (IdUtils.isPresent(userId)) {
            return groupConverter.convert(
                    groupRepository.findOneByUserId(userId).orElseThrow(
                            () -> new EntityNotFoundException("Student's group not found by user id: " + userId)
                    )
            );
        }
        throw new ValidationException("Bad parameters");
    }

    @Override
    public List<GroupResponse> findAllActive() {
        return null; //findAll(GroupStatus.OPEN);
    }

    @Override
    public List<GroupResponse> findAllInActive() {
        return null; //findAll(GroupStatus.CLOSE);
    }

    @Override
    public void numberUp(Long id) {
        changeNumber(id, true);
    }

    @Override
    public void numberDown(Long id) {
        changeNumber(id, false);
    }

    @Override
    public GroupResponse save(GroupRequest dto) {
        if (IdUtils.isNotPresent(dto.getId())) {
            return groupConverter.convert(
                    groupRepository.save(
                            groupConverter.convert(dto)
                    )
            );
        } else {
            StudentsGroup exists = groupRepository.findById(dto.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Student's group not found by id: " + dto.getId())
            );
            exists.setName(dto.getName());
            exists.setAdmissionDate(dto.getAdmissionDate());
            exists.setGraduationDate(dto.getGraduationDate());
            exists.setGroupStatus(GroupStatusConverter.convert(dto.getGroupStatus()));
            return groupConverter.convert(groupRepository.save(exists));
        }
    }

    @Override
    public GroupResponse update(GroupRequest dto) {
        return groupConverter.convert(
                groupRepository.save(
                        groupConverter.convert(dto)
                )
        );
    }

    @Override
    public void remove(Long id) {
        groupRepository.deleteById(id);
    }

//    private List<GroupResponse> findAll(GroupStatus groupStatus) {
//        return groupConverter.convert(groupRepository.findAllByGroupStatus(groupStatus));
//    }

    private void changeNumber(Long id, boolean up) {
        StudentsGroup exists = groupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Student's group not found by id: " + id)
        );
        exists.setNumber(up ? exists.getNumber() + 1 : exists.getNumber() - 1);
        groupRepository.save(exists);
    }
}
