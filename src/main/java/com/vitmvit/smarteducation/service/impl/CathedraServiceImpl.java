package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.converter.CathedraConverter;
import com.vitmvit.smarteducation.model.dto.request.FacultyRequest;
import com.vitmvit.smarteducation.model.dto.response.FacultyResponse;
import com.vitmvit.smarteducation.model.entity.Cathedra;
import com.vitmvit.smarteducation.repository.CathedraRepository;
import com.vitmvit.smarteducation.service.CathedraService;
import com.vitmvit.smarteducation.util.IdUtils;
import com.vitmvit.smarteducation.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.List;

@AllArgsConstructor
@Service
public class CathedraServiceImpl implements CathedraService {

    private final CathedraRepository cathedraRepository;
    private final CathedraConverter cathedraConverter;

    @Override
    public FacultyResponse findOne(Long id, String name) {
        if (IdUtils.isPresent(id)) {
            return cathedraConverter.convert(
                    cathedraRepository.findById(id).orElseThrow(
                            () -> new EntityNotFoundException("Cathedra not found by id: " + id)
                    )
            );
        } else if (StringUtils.isNotEmpty(name)) {
            return cathedraConverter.convert(
                    cathedraRepository.findOneByName(name).orElseThrow(
                            () -> new EntityNotFoundException("Cathedra not found by name: " + name)
                    )
            );
        }
        throw new ValidationException("Bad parameters");
    }

    @Override
    public List<FacultyResponse> findAll() {
        return cathedraConverter.convert(cathedraRepository.findAll());
    }

    @Override
    public FacultyResponse save(FacultyRequest dto) {
        if (IdUtils.isNotPresent(dto.getId())) {
            return cathedraConverter.convert(
                    cathedraRepository.save(
                            cathedraConverter.convert(dto)
                    )
            );
        } else {
            Cathedra exists = cathedraRepository.findById(dto.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Cathedra not found by id: " + dto.getId())
            );
            exists.setName(dto.getName());
            return cathedraConverter.convert(cathedraRepository.save(exists));
        }
    }

    @Override
    public void remove(Long id) {
        cathedraRepository.deleteById(id);
    }
}
