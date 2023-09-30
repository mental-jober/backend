/*
package com.fastcampus.jober.global;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.models.BlobUploadResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageControllerSample {

    private final AzureStorageProperties storageProperties;

    // 이미지 크기와 타입에 대한 10MB 제한 지정
    private static final long MAX_SIZE = 10 * 1024 * 1024;
    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
        "image/jpeg", "image/png", "image/gif"
    );

    @Autowired
    public ImageController(AzureStorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // 파일 크기 검증
        if (file.getSize() > MAX_SIZE) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("10MB를 초과하는 파일은 업로드할 수 없습니다.");
        }

        // 파일 형식 검증
        if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("jpg, png, gif 이외 파일은 업로드 할 수 없습니다.");
        }

        try {
            // Azure Blob Service 연결 설정
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(storageProperties.getConnectionString())
                .buildClient();

            // Blob Container 가져오기
            String containerName = storageProperties.getContainerName();
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName)
                .getBlobClient(file.getOriginalFilename());

            // Blob Container에 이미지 업로드
            BlobUploadResponse response = blobClient.upload(file.getInputStream(), file.getSize());

            if (response.getStatusCode() == HttpStatus.CREATED.value()) {
                String blobUrl = blobClient.getBlobUrl();
                return ResponseEntity.ok(blobUrl);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패!");
        }
    }
}
*/