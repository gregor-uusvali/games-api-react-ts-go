package com.example.backend.plant;

import com.example.backend.comment.CommentRepository;
import com.example.backend.comment.CommentService;
import com.example.backend.image.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    private final ImageService imageService;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    @Autowired
    public PlantService(PlantRepository plantRepository,
                        ImageService imageService,
                        CommentRepository commentRepository,
                        CommentService commentService) {
        this.plantRepository = plantRepository;
        this.imageService = imageService;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
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

    public List<?> getPlantsComments(Long id, int amount, int userId){
        System.out.println("#######################");
        System.out.println("Userid: " + userId);
        System.out.println("#######################");
        int pageSize = 10;
        int pageNumber = amount/pageSize -1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return commentService.getCommentsWithLikesByPlantId(id, userId, pageable);
    }

}
