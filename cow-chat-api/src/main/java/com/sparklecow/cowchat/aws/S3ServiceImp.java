package com.sparklecow.cowchat.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3ServiceImp implements S3Service{

    private final S3Client s3Client;

    @Value("${spring.destination.folder}")
    private String destinationFolder;

    @Override
    public String createBucket(String bucketName) {
        CreateBucketResponse response = s3Client.createBucket(bucketBuilder -> bucketBuilder.bucket(bucketName));
        return "Bucket creado en la ubicación: "+response.location();
    }

    @Override
    public String checkIfBucketExist(String bucketName) {
        try{
            s3Client.headBucket(headBucket -> headBucket.bucket(bucketName));
        }catch (S3Exception ex){
            return "El bucket "+bucketName+" no existe";
        }
        return "";
    }

    @Override
    public List<String> getAllBuckets() {
        ListBucketsResponse bucketsResponse = s3Client.listBuckets();
        if(!bucketsResponse.hasBuckets()){
            return List.of();
        }
        return bucketsResponse.buckets().stream().map(Bucket::name).toList();
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, Path fileLocation) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, fileLocation);
        return putObjectResponse.sdkHttpResponse().isSuccessful();
    }

    @Override
    public void downloadFile(String bucketName, String key) throws IndexOutOfBoundsException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> responseResponseBytes = s3Client.getObjectAsBytes(getObjectRequest);

        String fileName;
        if(key.contains("/")){
            fileName = key.substring(key.lastIndexOf("/"), key.length());
        }
        fileName = key;

        String filePath = Paths.get(destinationFolder, fileName).toString();

        File file = new File(filePath);
        file.getParentFile().mkdir();

    }

    @Override
    public String generatePresignedUploadUrl(String bucketName, String key, Duration duration) {
        return "";
    }

    @Override
    public String generatePresignedDownloadUrl(String bucketName, String key, Duration duration) {
        return "";
    }
}
