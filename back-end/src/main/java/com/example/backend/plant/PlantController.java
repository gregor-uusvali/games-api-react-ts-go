package com.example.backend.plant;

import com.example.backend.image.ImageService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.comment.Comment;

@RestController
@RequestMapping(path = "api/v1/plants")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PlantController {

    private final PlantService plantService;

    private final ImageService imageService;

    @Autowired
    public PlantController(PlantService plantService, ImageService imageService) {
        this.plantService = plantService;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Plant> getPlants() {
        return plantService.getPlants();
    }

    @GetMapping(path = "/user/{token}")
    public List<Plant> getPlantsBySessionToken(@PathVariable String token) {
        return plantService.getPlantsBySessionToken(token);
    }

    @Autowired
    private ServletContext servletContext;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPlant(@RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("instruction") String instruction,
                                      @RequestParam("image") MultipartFile imageFile,
                                      @RequestParam("date") LocalDate date,
                                      @RequestParam("userId") String userId) {
        if (imageFile.getSize() >= 5242880) { // over 5MB
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Image to large (5MB limit)");
        }
        String filename = imageService.saveUploadedImg(imageFile);

        if (filename != "Error") {

            Plant plant = new Plant();
            plant.setName(name);
            plant.setDescription(description);
            plant.setInstruction(instruction);
            plant.setImage("http://localhost:8080/images/" + filename);
            plant.setDate(date);
            plant.setUserId(Integer.parseInt(userId));

            Plant addedPlant = plantService.addPlant(plant);

            return ResponseEntity.ok(addedPlant);

        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }

    }

    @PutMapping("/{id}")
    public void updatePlant(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("instruction") String instruction,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
        String filename = null; // Initialize the filename to null

        if (imageFile != null && !imageFile.isEmpty()) {
            filename = imageService.saveUploadedImg(imageFile);
        }

        plantService.updatePlant(id, name, description, filename, instruction);
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) throws IOException {
        plantService.deletePlant(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlant(@PathVariable String id) {
        try {
            Plant plant = plantService.getPlantById(Long.parseLong(id));
            return ResponseEntity.ok(plant);
        } catch (RuntimeException e) {
            String errorMessage = "Plant not found with id: " + id;
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/comments/{id}")
    public List<?> getPlantsComments(@PathVariable Long id, @RequestParam int amount, @RequestHeader("userID") int userId) {
        return plantService.getPlantsComments(id, amount, userId);

    }
}
