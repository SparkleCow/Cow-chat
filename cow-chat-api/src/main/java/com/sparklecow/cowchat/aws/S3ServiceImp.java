package com.sparklecow.cowchat.aws;

import com.sparklecow.cowchat.aws.config.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class S3ServiceImp implements S3Service{

    private final S3Client s3Client;
    private final AwsProperties awsProperties;

    @Value("${spring.destination.folder}")
    private String destinationFolder;

    @Override
    public Boolean uploadFile(String key, Path fileLocation) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(awsProperties.getBucket())
                .key(key)
                .build();

        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, fileLocation);
        return putObjectResponse.sdkHttpResponse().isSuccessful();
    }

    @Override
    public void downloadFile(String key) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(awsProperties.getBucket())
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> responseResponseBytes = s3Client.getObjectAsBytes(getObjectRequest);

        String fileName;
        if(key.contains("/")){
            fileName = key.substring(key.lastIndexOf("/"));
        }else{
            fileName = key;
        }
        String filePath = Paths.get(destinationFolder, fileName).toString();

        File file = new File(filePath);
        file.getParentFile().mkdir();

        try(FileOutputStream fos = new FileOutputStream(file)){
            fos.write(responseResponseBytes.asByteArray());
        }catch (IOException exception){
            throw new IOException("Error al descargar el archivo "+exception.getMessage());
        }
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
