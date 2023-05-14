package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.converter.AvatarConverter;
import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.dto.response.Base64Response;
import com.vitmvit.smarteducation.model.entity.Avatar;
import com.vitmvit.smarteducation.repository.AvatarRepository;
import com.vitmvit.smarteducation.service.AvatarService;
import com.vitmvit.smarteducation.util.AttachmentUtils;
import com.vitmvit.smarteducation.util.FileStorageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;

@AllArgsConstructor
@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final AvatarConverter avatarConverter;

    @Override
    public Base64Response getBase64ByUuid(String uuid) {
        Avatar avatar = avatarRepository.findByGeneratedName(uuid).orElse(null);
        return avatar == null
                ? new Base64Response()
                : AttachmentUtils.getBase64(AttachmentUtils.getAttachmentFile(avatar));
    }

    @Override
    public AvatarResponse getDtoByUuid(String uuid) {
        return avatarConverter.convert(
                avatarRepository.findByGeneratedName(uuid).orElseThrow(
                        () -> new EntityNotFoundException("Avatar not found by UUID: " + uuid)
                )
        );
    }

    @Override
    public AvatarResponse save(MultipartFile multipartFile) {
        return avatarConverter.convert(avatarRepository.save(AttachmentUtils.saveAvatar(multipartFile)));
    }

    @Override
    public void removeByUuid(String uuid) {
        Avatar avatar = avatarRepository.findByGeneratedName(uuid).orElseThrow(
                () -> new EntityNotFoundException("Avatar not found by UUID: " + uuid)
        );
        File file = new File(FileStorageUtils.fileStorage() + avatar.getGeneratedPath() + avatar.getGeneratedName());
        if (file.exists()) {
            if (file.isFile() && file.delete()) {
                avatarRepository.deleteByGeneratedName(uuid);
                if (file.exists()) {
                    throw new RuntimeException("Cannot remove file: " + avatar.getGeneratedName());
                }
            }
        } else {
            avatarRepository.deleteByGeneratedName(uuid);
        }
    }
}
