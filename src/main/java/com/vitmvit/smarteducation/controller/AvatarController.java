package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.dto.response.Base64Response;
import com.vitmvit.smarteducation.service.AvatarService;
import com.vitmvit.smarteducation.util.FileStorageUtils;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth/attachments/avatars")
public class AvatarController {

    private final AvatarService avatarService;

    /**
     * получение изображения в кодировке base64
     */
    @GetMapping("/base64")
    public ResponseEntity<Base64Response> getBase64ByUuid(@RequestParam String uuid) {
        return new ResponseEntity<>(avatarService.getBase64ByUuid(uuid), HttpStatus.OK);
    }

    /**
     * получение изображения как есть
     */
    @GetMapping("/resource")
    public ResponseEntity<Resource> getResourceByUuid(@RequestParam String uuid) {
        AvatarResponse avatar = avatarService.getDtoByUuid(uuid);
        return avatar == null ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new File(FileStorageUtils.fileStorage() + avatar.getGeneratedPath() + avatar.getGeneratedName()).exists() ?
                        ResponseEntity
                                .ok()
                                .contentType(MediaType.parseMediaType(avatar.getMimeType()))
                                .header(HttpHeaders.CONTENT_DISPOSITION, "avatar; filename=\"" + avatar.getOriginalName() + "\"")
                                .body(avatarService.getResourceByUuid(uuid)) :
                        new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<AvatarResponse> save(@RequestPart("avatar") MultipartFile multipartFile) {
        return new ResponseEntity<>(avatarService.save(multipartFile), HttpStatus.OK);
    }
}
