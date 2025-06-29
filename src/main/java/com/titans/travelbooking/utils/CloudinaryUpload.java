package com.titans.travelbooking.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.titans.travelbooking.exception.CloudinaryUploadException;
import com.titans.travelbooking.exception.InvalidFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CloudinaryUpload {

    private static final long MAX_FILE_SIZE_BYTES = 2 * 1024 * 1024; // 2 MB

    private static final List<String> ALLOWED_CONTENT_TYPES = List.of(
            "image/jpeg", "image/png", "image/gif", "image/webp"
    );

    private final Cloudinary cloudinary;

    public CloudinaryUpload(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadMedia(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new InvalidFileException("File is empty or null.");
            }

            String contentType = file.getContentType();
            if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
                throw new InvalidFileException("Unsupported file type: " + contentType);
            }

            if (file.getSize() > MAX_FILE_SIZE_BYTES) {
                throw new InvalidFileException("File size exceeds limit of 5 MB");
            }

            log.info("Uploading file: {}", file.getOriginalFilename());

            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl =(String) result.get("secure_url");

            log.info("Upload successful: {}", imageUrl);
            return imageUrl;

        } catch (IOException ex) {
            log.error("Failed to upload file to Cloudinary", ex);
            throw new CloudinaryUploadException("Failed to upload file to Cloudinary", ex);
        }
    }
}
