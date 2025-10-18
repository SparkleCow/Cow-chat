package com.sparklecow.cowchat.common.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService{

    /**
     * Compress the provided file data using an GZIP  algorithm.
     *
     * @param data Raw file bytes to compress.
     * @return Compressed file bytes.
     */
    byte[] compress(byte[] data);

    /**
     * Decompress previously compressed file data.
     *
     * @param compressedData Compressed file bytes.
     * @return Original (decompressed) file bytes.
     */
    byte[] decompress(byte[] compressedData);

    /**
     * Encrypt the provided file data using a symmetric encryption algorithm (AES).
     *
     * @param data File bytes to encrypt.
     * @return Encrypted file bytes.
     */
    byte[] encrypt(byte[] data);

    /**
     * Decrypt previously encrypted file data.
     *
     * @param encryptedData Encrypted file bytes.
     * @return Original (decrypted) file bytes.
     */
    byte[] decrypt(byte[] encryptedData);

    /**
     * Upload file bytes to AWS S3.
     *
     * @param data     File bytes to upload (may be compressed/encrypted).
     * @param path     Destination path or folder inside the S3 bucket.
     * @param filename Name of the file.
     * @return Public URL or key of the uploaded file.
     */
    String uploadToS3(byte[] data, String path, String filename);

    /**
     * Download file bytes from AWS S3.
     *
     * @param fileUrl Public URL or key of the file in S3.
     * @return Raw file bytes (exactly as stored).
     */
    byte[] downloadFromS3(String fileUrl);

    /**
     * Complete process: compress → encrypt → upload.
     *
     * @param file Multipart file received from the client.
     * @param path Destination path or folder in the S3 bucket.
     * @return Public URL of the uploaded file.
     */
    String processAndUpload(MultipartFile file, String path);

    /**
     * Complete reverse process: download → decrypt → decompress.
     *
     * @param fileUrl Public URL or key of the file in S3.
     * @return Restored original file bytes.
     */
    byte[] downloadAndRestore(String fileUrl);
}
