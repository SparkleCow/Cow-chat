package com.sparklecow.cowchat.aws.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.socialmessaging.model.S3PresignedUrl;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final AwsProperties awsProperties;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(awsProperties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        awsProperties.getKey(),
                                        awsProperties.getSecret()
                                )
                        )
                )
                .build();
    }

    @Bean
    public S3Presigner s3Presigner(){
        return S3Presigner.builder()
                .region(Region.of(awsProperties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        awsProperties.getKey(),
                                        awsProperties.getSecret()
                                )
                        )
                )
                .build();
    }

}