package com.sparklecow.cowchat.aws;

import com.sparklecow.cowchat.common.file.FileService;
import com.sparklecow.cowchat.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("test")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PruebaController {

    @Value("${spring.destination.folder}")
    private String destinationFolder;
    private final S3Service s3Service;
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam String key,
                                             @RequestPart MultipartFile file,
                                             Authentication authentication) throws IOException {
        return ResponseEntity.ok(fileService.uploadProfileImageToS3(file, key, (User) authentication.getPrincipal()));
    }

    @PostMapping("/download")
    public ResponseEntity<String> downloadFile(@RequestParam String key) throws IOException {
        s3Service.downloadFile(key);
        return ResponseEntity.ok("Archivo descargado correctamente");
    }

}
