package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.converter.GroupConverter;
import com.vitmvit.smarteducation.model.constant.GroupStatus;
import com.vitmvit.smarteducation.model.dto.response.GroupResponse;
import com.vitmvit.smarteducation.model.entity.SGroup;
import com.vitmvit.smarteducation.repository.GroupRepository;
import com.vitmvit.smarteducation.service.GroupService;
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
    public GroupResponse findOne(Long id, String name) {
        if (id != null) {
            return groupConverter.convert(
                    groupRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("Subject not found by id: " + id)
                    )
            );
        } else if (StringUtils.isNotEmpty(name)) {
            return groupConverter.convert(
                    groupRepository.findOneByName(name).orElseThrow(
                            () -> new EntityNotFoundException("Subject not found by name: " + name)
                    )
            );
        }
        throw new ValidationException("Bad parameters");
    }

    @Override
    public List<GroupResponse> findAllActive() {
        return findAll(GroupStatus.OPEN);
    }

    @Override
    public List<GroupResponse> findAllInActive() {
        return findAll(GroupStatus.CLOSE);
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
    public void remove(Long id) {
        groupRepository.deleteById(id);
    }

    private List<GroupResponse> findAll(GroupStatus groupStatus) {
        return groupConverter.convert(groupRepository.findAllByGroupStatus(groupStatus));
    }

    private void changeNumber(Long id, boolean up) {
        SGroup sGroup = groupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Group not found by id: " + id)
        );
        sGroup.setNumber(up ? sGroup.getNumber() + 1 : sGroup.getNumber() - 1);
        groupRepository.save(sGroup);
    }
}
