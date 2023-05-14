package com.vitmvit.smarteducation.util;

import com.vitmvit.smarteducation.model.dto.response.Base64Response;
import com.vitmvit.smarteducation.model.entity.Avatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@UtilityClass
public class AttachmentUtils {

    public static Avatar saveAvatar(MultipartFile multipartFile) {
        try {
            return saveAsFile(multipartFile);
        } catch (IOException ex) {
            throw new RuntimeException("Error save avatar file: " + ex.getMessage());
        }
    }

    private static Avatar saveAsFile(MultipartFile multipartFile) throws IOException {
        File fileStorageDir = new File(FileStorageUtils.fileStorage());
        if (!fileStorageDir.exists() && !fileStorageDir.isDirectory()) {
            fileStorageDir.mkdirs();
        }
        String mimeType = Objects.requireNonNull(multipartFile.getContentType());
        String generatedPath = getFilePath(mimeType);
        File absolutelyPath = new File(FileStorageUtils.fileStorage() + generatedPath);
        if (!absolutelyPath.exists() && !absolutelyPath.isDirectory()) {
            absolutelyPath.mkdirs();
        }
        if (!absolutelyPath.exists()) {
            throw new RuntimeException("Cannot make dir: " + absolutelyPath.getAbsolutePath());
        }
        String generatedName = UUID.randomUUID().toString(); // getFullName(multipartFile);
        FileOutputStream fileOutputStream = new FileOutputStream(absolutelyPath.getAbsolutePath() + "/" + generatedName);
        byte[] multipartFileBytes = multipartFile.getBytes();
        fileOutputStream.write(multipartFileBytes, 0, multipartFileBytes.length);
        Avatar avatar = new Avatar();
        avatar.setOriginalName(multipartFile.getOriginalFilename());
        avatar.setGeneratedPath(generatedPath);
        avatar.setGeneratedName(generatedName);
        //avatar.setMimeType(getFileType(mimeType));
        avatar.setMimeType(multipartFile.getContentType());
        return avatar;
    }

    public static AttachmentDto getAttachmentFile(Avatar avatar) {
        String baseFolder = FileStorageUtils.fileStorage();
        baseFolder = baseFolder.endsWith("/") ? baseFolder : baseFolder.concat("/");
        String filePathName = baseFolder + avatar.getGeneratedPath() + avatar.getGeneratedName();
        return new AttachmentUtils.AttachmentDto(
                avatar.getOriginalName(),
                avatar.getMimeType(),
                filePathName,
                new File(filePathName)
        );
    }

    public static Base64Response getBase64(AttachmentUtils.AttachmentDto data) {
        Base64Response base64Response = new Base64Response();
        base64Response.setOriginalName(data.getOriginalName());
        base64Response.setMimeType(data.getMimeType());
        if (data.getAvatarFile().isFile() && data.getAvatarFile().exists()) {
            try {
                base64Response.setBase64(
                        Base64.getEncoder().encodeToString(
                                FileUtils.readFileToByteArray(data.getAvatarFile())
                        )
                );
            } catch (IOException ex) {
                throw new RuntimeException("Avatar file not converted to base64: " + ex);
            }
        } else {
            try {
                throw new FileNotFoundException("Avatar file not found by path: " + data.getFilePathName());
            } catch (FileNotFoundException ignore) {
            }
        }
        return base64Response;
    }

    public static boolean remove(String filePathName) {
        return new File(filePathName).delete();
    }

    //------------------------------------------------------------------------------------------------------------------

    /*
     * возвращает относительный путь к файлу
     * пример: /img/a/b/c/
     */
    private static String getFilePath(String mimeType) {
        return mimeType == null ? null : getRandomPath();
    }

    /**
     * создает путь состоящий из букв, пример: a/b/c/
     */
    private static String getRandomPath() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int d = 0; d < FileStorageUtils.fileDeep(); d++) {
            stringBuilder.append(getLetter()).append("/");
        }
        return stringBuilder.toString();
    }

    /**
     * возвращает случайную букву от 'a' до 'z'
     * a = 97
     * z = 122
     */
    private static String getLetter() {
        int min = 97;
        int max = 122;
        return String.valueOf((char) (new Random().nextInt((max - min) + 1) + min));
    }

    //------------------------------------------------------------------------------------------------------------------

    @AllArgsConstructor
    @Getter
    @Setter
    public static class AttachmentDto {

        private String originalName;
        private String mimeType;
        private String filePathName;
        private File avatarFile;
    }
}
