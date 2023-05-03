package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.model.dto.response.GroupResponse;
import com.vitmvit.smarteducation.service.GroupService;
import com.vitmvit.smarteducation.validate.GroupExists;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class GroupExistsValidator implements ConstraintValidator<GroupExists, GroupResponse> {

    private GroupService groupService;

    @Override
    public boolean isValid(GroupResponse groupResponse, ConstraintValidatorContext constraintValidatorContext) {
        return !groupService.exists(groupResponse.getName() + groupResponse.getNumber());
    }
}
