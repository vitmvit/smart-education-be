package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.converter.AttachmentConverter;
import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.dto.response.Base64Response;
import com.vitmvit.smarteducation.model.entity.Avatar;
import com.vitmvit.smarteducation.repository.AttachmentRepository;
import com.vitmvit.smarteducation.service.AttachmentService;
import com.vitmvit.smarteducation.util.AttachmentUtils;
import com.vitmvit.smarteducation.util.FileStorageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@AllArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentConverter attachmentConverter;

    @Override
    public Base64Response getBase64ByUuid(String uuid) {
        Avatar avatar = attachmentRepository.findByGeneratedName(uuid).orElse(null);
        return avatar == null
                ? new Base64Response()
                : AttachmentUtils.getBase64(AttachmentUtils.getAttachmentFile(avatar));
    }

    @Override
    public AvatarResponse getDtoByUuid(String uuid) {
        return attachmentConverter.convert(
                attachmentRepository.findByGeneratedName(uuid).orElseThrow(
                        () -> new EntityNotFoundException("Avatar not found by UUID: " + uuid)
                )
        );
    }

    @Override
    public AvatarResponse save(MultipartFile multipartFile) {
        return attachmentConverter.convert(attachmentRepository.save(AttachmentUtils.saveAvatar(multipartFile)));
    }

    @Override
    public void removeByUuid(String uuid) {
        Avatar avatar = attachmentRepository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Avatar not found by UUID: " + uuid)
        );
        File file = new File(FileStorageUtils.fileStorage() + avatar.getGeneratedPath() + avatar.getGeneratedName());
        if (file.exists()) {
            if (file.isFile() && file.delete()) {
                attachmentRepository.deleteByGeneratedName(uuid);
                if (file.exists()) {
                    throw new RuntimeException("Cannot remove file: " + avatar.getGeneratedName());
                }
            }
        } else {
            attachmentRepository.deleteByGeneratedName(uuid);
        }
    }
}
