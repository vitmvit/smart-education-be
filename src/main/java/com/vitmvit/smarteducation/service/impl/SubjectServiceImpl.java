package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.converter.SubjectConverter;
import com.vitmvit.smarteducation.model.dto.request.SubjectRequest;
import com.vitmvit.smarteducation.model.dto.response.SubjectResponse;
import com.vitmvit.smarteducation.model.entity.Subject;
import com.vitmvit.smarteducation.repository.SubjectRepository;
import com.vitmvit.smarteducation.service.SubjectService;
import com.vitmvit.smarteducation.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;

@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectConverter subjectConverter;

    @Override
    public SubjectResponse findOne(Long id, String name) {
        if (id != null) {
            return subjectConverter.convert(
                    subjectRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("Subject not found by id: " + id)
                    )
            );
        } else if (StringUtils.isNotEmpty(name)) {
            return subjectConverter.convert(
                    subjectRepository.findOneByName(name).orElseThrow(
                            () -> new EntityNotFoundException("Subject not found by name: " + name)
                    )
            );
        }
        throw new ValidationException("Bad parameters");
    }

    @Override
    public List<SubjectResponse> findAll() {
        return subjectConverter.convert(subjectRepository.findAll());
    }

    @Override
    public SubjectResponse save(SubjectRequest dto) {
        if (dto.getId() == null) {
            return subjectConverter.convert(
                    subjectRepository.save(
                            subjectConverter.convert(dto)
                    )
            );
        } else {
            Subject subject = subjectRepository.findById(dto.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Subject not found by id: " + dto.getId())
            );
            subject.setName(dto.getName());
            return subjectConverter.convert(subjectRepository.save(subject));
        }
    }

    @Override
    public void remove(Long id) {
        subjectRepository.deleteById(id);
    }
}
