package vn.hoidanit.jobhunter.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.jobhunter.util.error.StorageException;

@Service
public class FileService {

    @Value("${hoidanit.upload-file.base-uri}")
    private String baseURI;

    public void createDirectory(String folder) throws URISyntaxException {
        // CHUẨN HÓA: Cắt bỏ "file://" và dùng constructor 4 tham số để tránh lỗi
        // Windows Path
        String cleanPath = folder.replace("file://", "");
        URI uri = new URI("file", null, cleanPath, null);

        Path path = Paths.get(uri);
        File tmpDir = new File(path.toString());

        if (!tmpDir.isDirectory()) {
            try {
                Files.createDirectory(tmpDir.toPath());
                System.out.println(">>> CREATE NEW DIRECTORY SUCCESSFUL, PATH = " + tmpDir.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(">>> SKIP MAKING DIRECTORY SUCCESSFUL, ALREADY EXIST");
        }
    }

    public String store(MultipartFile file, String folder) throws URISyntaxException, IOException {
        String finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

        // Đưa baseURI về dạng Path sạch trước
        Path basePath = Paths.get(new URI(baseURI));

        // Dùng resolve để Java tự động ráp các phân đoạn thư mục hợp lệ theo OS
        // (Windows/Linux)
        Path targetPath = basePath.resolve(folder).resolve(finalName);

        System.out.println(">>> TRONG CODE STORE (GHI FILE): " + targetPath.toAbsolutePath().toString());
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return finalName;
    }

    public long getFileLength(String fileName, String folder) throws URISyntaxException {
        Path basePath = Paths.get(new URI(baseURI));
        Path targetPath = basePath.resolve(folder).resolve(fileName);

        File tmpDir = targetPath.toFile();

        if (!tmpDir.exists() || tmpDir.isDirectory()) {
            return 0;
        }
        return tmpDir.length();
    }

    public InputStreamResource getResource(String fileName, String folder)
            throws URISyntaxException, FileNotFoundException, StorageException {

        Path basePath = Paths.get(new URI(baseURI));
        Path targetPath = basePath.resolve(folder).resolve(fileName);

        System.out.println(">>> TRONG CODE GET_RESOURCE (ĐỌC FILE): " + targetPath.toAbsolutePath().toString());

        File file = targetPath.toFile();

        if (!file.exists()) {
            throw new StorageException("File with name = " + fileName + " not found");
        }

        return new InputStreamResource(new FileInputStream(file));
    }
}