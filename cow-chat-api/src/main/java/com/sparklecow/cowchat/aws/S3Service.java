package com.sparklecow.cowchat.aws;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;

public interface S3Service {

    Boolean uploadFile(String key, Path fileLocation);

    void downloadFile(String key) throws IOException;

    String generatePresignedUploadUrl(String bucketName, String key, Duration duration);

    String generatePresignedDownloadUrl(String bucketName, String key, Duration duration);
}
