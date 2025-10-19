package com.sparklecow.cowchat.common.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

@Service
@RequiredArgsConstructor
public class FileServiceImp implements FileService{

    private final S3Client s3Client;

    @Override
    public byte[] compress(byte[] data) {
        return new byte[0];
    }

    @Override
    public byte[] decompress(byte[] compressedData) {
        return new byte[0];
    }

    @Override
    public byte[] encrypt(byte[] data) {
        return new byte[0];
    }

    @Override
    public byte[] decrypt(byte[] encryptedData) {
        return new byte[0];
    }

    @Override
    public String uploadToS3(byte[] data, String path, String filename) {
        return "";
    }

    @Override
    public byte[] downloadFromS3(String fileUrl) {
        return new byte[0];
    }

    @Override
    public String processAndUpload(MultipartFile file, String path) {
        return "";
    }

    @Override
    public byte[] downloadAndRestore(String fileUrl) {
        return new byte[0];
    }

    /*@Value("${aws.s3.bucket}")
    private String bucketName;

    // AES encryption key (16 bytes = 128 bits). In production, store this securely!
    @Value("${file.encryption.secret}")
    private String secretKey;

    // --------------------------------------------------
    // Compression / Decompression
    // --------------------------------------------------

    @Override
    public byte[] compress(byte[] data) {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {
            gzipStream.write(data);
            gzipStream.finish();
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error compressing file", e);
        }
    }

    @Override
    public byte[] decompress(byte[] compressedData) {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(compressedData);
             GZIPInputStream gzipStream = new GZIPInputStream(byteStream);
             ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            return outStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error decompressing file", e);
        }
    }

    // --------------------------------------------------
    // Encryption / Decryption (AES)
    // --------------------------------------------------

    private Key getAesKey() {
        // Make sure key is 16 bytes long (AES-128)
        byte[] keyBytes = secretKey.getBytes();
        if (keyBytes.length != 16) {
            throw new IllegalArgumentException("AES key must be 16 bytes long");
        }
        return new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, getAesKey());
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting file", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, getAesKey());
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting file", e);
        }
    }

    // --------------------------------------------------
    // AWS S3 Upload / Download
    // --------------------------------------------------

    @Override
    public String uploadToS3(byte[] data, String path, String filename) {
        String key = path + "/" + filename;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentLength((long) data.length)
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(data));

        return key; // return key instead of public URL (recommended for private buckets)
    }

    @Override
    public byte[] downloadFromS3(String fileKeyOrUrl) {
        // Assume fileKeyOrUrl is the S3 object key
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKeyOrUrl)
                .build();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            s3Client.getObject(request, software.amazon.awssdk.core.sync.ResponseTransformer.toOutputStream(outputStream));
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file from S3", e);
        }
    }

    @Override
    public String processAndUpload(MultipartFile file, String path) {
        return "";
    }

    // --------------------------------------------------
    // Combined Operations
    // --------------------------------------------------

    @Override
    public String processAndUpload(MultipartFile file, String path) {
        try {
            // Step 1: Read file bytes
            byte[] data = file.getBytes();

            // Step 2: Compress
            byte[] compressed = compress(data);

            // Step 3: Encrypt
            byte[] encrypted = encrypt(compressed);

            // Step 4: Upload to S3
            return uploadToS3(encrypted, path, file.getOriginalFilename());

        } catch (IOException e) {
            throw new RuntimeException("Error processing and uploading file", e);
        }
    }

    @Override
    public byte[] downloadAndRestore(String fileKeyOrUrl) {
        // Step 1: Download
        byte[] encryptedData = downloadFromS3(fileKeyOrUrl);

        // Step 2: Decrypt
        byte[] decrypted = decrypt(encryptedData);

        // Step 3: Decompress
        return decompress(decrypted);
    }*/
}
