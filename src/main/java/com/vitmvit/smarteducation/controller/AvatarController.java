package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.response.AvatarResponse;
import com.vitmvit.smarteducation.model.dto.response.Base64Response;
import com.vitmvit.smarteducation.service.AvatarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping
    public ResponseEntity<AvatarResponse> save(@RequestPart("avatar") MultipartFile multipartFile) {
        return new ResponseEntity<>(avatarService.save(multipartFile), HttpStatus.OK);
    }
}
