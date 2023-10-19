package com.example.backend.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

@Service
public class ImageService {

    static final float MAX_COMPRESSION_SIZE = 800; //800kB


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
//            System.out.println(uploadPath);


//            byte[] orgImage = imageFile.getBytes();
//
//            orgImage = compressImage(orgImage,1);
//            Path path = Paths.get(uploadPath + "\\" + filename);
//            Files.write(path, orgImage);
            //Save the image file to the directory on the server
            imageFile.transferTo(new File(uploadPath, filename));

            return filename;

        } catch (IOException e) {
            // Handle the exception (e.g., return an error response)
            e.printStackTrace();
            return "Error";
        }
    }

    public static byte[] compressImage(byte[] bytes, float imageQuality) throws IOException {

        float kbSize = bytes.length / 1024f;

        if (kbSize <= MAX_COMPRESSION_SIZE || imageQuality <= 0) {
            return bytes;
        } else {
            //https://docs.oracle.com/javase/7/docs/api/java/io/ByteArrayOutputStream.html
            // data is written into a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Get image writers
            Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName(
                    "jpg"); // Input your Format Name here

            if (!imageWriters.hasNext()) {
                throw new IllegalStateException("Writers Not Found!!");
            }
            //https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageWriter.html
            ImageWriter imageWriter = imageWriters.next();
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            imageWriter.setOutput(imageOutputStream);

            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();

            // Set the compress quality metrics
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imageQuality);

            // Create the buffered image
            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);

            //Remove alpha channel to avoid (bogus input errors)
            //what is - https://www.makeuseof.com/tag/alpha-channel-images-mean/
            bufferedImage = removeAlphaChannel(bufferedImage);

            // Compress and insert the image into the byte array.
            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);

            // close all streams !important
            inputStream.close();
            outputStream.close();
            imageOutputStream.close();
            imageWriter.dispose();

            bytes = outputStream.toByteArray();

            return compressImage(bytes, imageQuality - .05f);
        }

    }

    private static BufferedImage removeAlphaChannel(BufferedImage img) {
        if (!img.getColorModel().hasAlpha()) {
            return img;
        }

        BufferedImage target = createImage(img.getWidth(), img.getHeight(), false);
        Graphics2D g = target.createGraphics();
        // g.setColor(new Color(color, false));
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return target;
    }

    private static BufferedImage createImage(int width, int height, boolean hasAlpha) {
        return new BufferedImage(width, height,
                hasAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
    }

    public void deleteImgFile(String imagePath) throws IOException {
        try{
            if (imagePath != null && !imagePath.isEmpty()) {

                int lastIndex = imagePath.lastIndexOf('/');

                String fileName = imagePath.substring(lastIndex + 1);
                Path fileToDeletePath = Paths.get("uploads/" + fileName);
                Files.delete(fileToDeletePath);
                System.out.println(fileName);
            }
        }catch(IOException e) {
        }
    }
}
