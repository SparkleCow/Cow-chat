package com.sparklecow.cowchat.aws;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public interface S3Service {

    String createBucket(String bucketName);

    String checkIfBucketExist(String bucketName);

    List<String> getAllBuckets();

    Boolean uploadFile(String bucketName, String key, Path fileLocation);

    void downloadFile(String bucketName, String key) throws IndexOutOfBoundsException;

    String generatePresignedUploadUrl(String bucketName, String key, Duration duration);

    String generatePresignedDownloadUrl(String bucketName, String key, Duration duration);
}
