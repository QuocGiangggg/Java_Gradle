package vn.hoidanit.jobhunter.controller;

import java.awt.ItemSelectable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.jobhunter.domain.file.ResUploadFileDTO;
import vn.hoidanit.jobhunter.service.FileService;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.error.StorageException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    @Value("${hoidanit.upload-file.base-uri}")
    private String baseURI;

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/files")
    @ApiMessage("Upload single file")
    public ResponseEntity<ResUploadFileDTO> upload(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("folder") String folder)
            throws URISyntaxException, IOException, StorageException {

        // skip validate

        if (file == null || file.isEmpty()) {
            throw new StorageException("File is Empty. Please upload a file...");
        }

        String fileName = file.getOriginalFilename();
        List<String> allowedExtentions = Arrays.asList("pdf", "jpg", "jpeg", "png", "doc", "docx");

        boolean isValid = allowedExtentions.stream().anyMatch(item -> fileName.toLowerCase().endsWith(item));

        if (!isVlaid) {
            throw new StorageException("InValid Extentions. only allowed" + allowedExtentions.toString());
        }
        // create a directory if not exist
        this.fileService.createDirectory(baseURI + folder);
        // store file
        String uploadFile = this.fileService.store(file, folder);

        ResUploadFileDTO res = new ResUploadFileDTO(uploadFile, Instant.now());

        return ResponseEntity.ok().body(res);
    }
}
