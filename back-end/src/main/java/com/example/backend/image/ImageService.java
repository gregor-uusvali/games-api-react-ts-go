package com.example.backend.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class ImageService {
    public String saveUploadedImg(MultipartFile imageFile) {
        try {
            long timestamp = System.currentTimeMillis();

            // Extract the original file extension (e.g., .jpg)
            String originalFileName = imageFile.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // Construct a unique filename with timestamp
            String filename = timestamp + fileExtension;

            Path currRelativePath = Paths.get("");
            String currAbsolutePathString = currRelativePath.toAbsolutePath().toString();

            // Get the absolute path to the images directory
            String uploadPath = currAbsolutePathString + "\\uploads";
            System.out.println(uploadPath);

            // Save the image file to the directory on the server
            imageFile.transferTo(new File(uploadPath, filename));

            return filename;

        } catch (IOException e) {
            // Handle the exception (e.g., return an error response)
            e.printStackTrace();
            return "Error";
        }
    }
}
