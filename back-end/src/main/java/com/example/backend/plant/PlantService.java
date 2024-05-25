package com.example.backend.plant;

import com.example.backend.comment.CommentRepository;
import com.example.backend.image.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.backend.comment.Comment;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    private final ImageService imageService;
    private final CommentRepository commentRepository;
    @Autowired
    public PlantService(PlantRepository plantRepository,
                        ImageService imageService,
                        CommentRepository commentRepository) {
        this.plantRepository = plantRepository;
        this.imageService = imageService;
        this.commentRepository = commentRepository;
    }

    public List<Plant> getPlants(){
        return plantRepository.findAll();
    }

    public List<Plant> getPlantsBySessionToken(String token) {
        return plantRepository.getPlantsBySessionToken(token);
    }


    public Plant addPlant(Plant plant) {
        System.out.println(plant);
        return plantRepository.save(plant);
    }

    @Transactional
    public void updatePlant(Long id, String name, String description, String image, String instruction) throws IOException {
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));

        // Update the plant's attributes
        if(image != null){

            String imagePath = existingPlant.getImage();
            imageService.deleteImgFile(imagePath);
            existingPlant.setImage("http://localhost:8080/images/" + image);
        }
        existingPlant.setName(name);
        existingPlant.setDescription(description);
        existingPlant.setInstruction(instruction);
        System.out.println(existingPlant);
        plantRepository.save(existingPlant);
    }

    @Transactional
    public void deletePlant(Long id) throws IOException {
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));

        // Retrieve the file path of the associated image
        String imagePath = existingPlant.getImage();

        imageService.deleteImgFile(imagePath);

        // Save the updated plant
        plantRepository.delete(existingPlant);
    }
    public Plant getPlantById(Long id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found with id: " + id));
        return plant;
    }

    public List<Comment> getPlantsComments(Long id){
        System.out.println(commentRepository.getCommentsByPlantId(id));
        return commentRepository.getCommentsByPlantId(id);
    }

}
