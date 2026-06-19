package vn.hoidanit.jobhunter.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Value("${hoidanit.upload-file.base-uri}")
    private String baseURI;

    public void createDirectory(String folder) throws URISyntaxException {
        // Sử dụng URI.create để tự động chuẩn hóa chuỗi URL bao gồm cả khoảng trắng nếu
        // có
        URI uri = URI.create(folder);
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
        // Khi truyền tách biệt: scheme ("file"), host (null), và path,
        // Java sẽ TỰ ĐỘNG mã hóa khoảng trắng sang %20 một cách hợp lệ.

        // Cắt bỏ tiền tố "file://" trong baseURI để lấy đường dẫn thuần túy
        String cleanPath = baseURI.replace("file://", "") + folder + "/" + finalName;

        // Khởi tạo URI bằng constructor 4 tham số an toàn bảo mật ký tự đặc biệt
        URI uri = new URI("file", null, cleanPath, null);

        Path path = Paths.get(uri);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        }

        return finalName;
    }
}