package com.example.backend.plant;

import com.example.backend.user.User;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/plants")
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getPlants() {
        return plantService.getPlants();
    }

    // @PostMapping(path="/add")
    // public ResponseEntity<?> addPlant(@RequestBody Plant plant) {
    // Plant addedPlant = plantService.addPlant(plant);

    // // You can return a success response or the saved user object
    // return ResponseEntity.ok(addedPlant);
    // }
    @Autowired
    private ServletContext servletContext;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addPlant(@RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("instruction") String instruction,
                                      @RequestParam("image") MultipartFile imageFile,
                                      @RequestParam("date") LocalDate date ) {
        System.out.println(name);
        System.out.println(imageFile);
        System.out.println(date);
        try {
            // Generate a timestamp
            long timestamp = System.currentTimeMillis();

            // Extract the original file extension (e.g., .jpg)
            String originalFileName = imageFile.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // Construct a unique filename with timestamp
            String filename = timestamp + fileExtension;

            // Get the absolute path to the images directory
            String absoluteImagePath = "C:\\Users\\user\\OneDrive\\Documents\\Projects\\personal-github\\plants-api-react-ts-java\\back-end\\uploads";
            System.out.println(absoluteImagePath);
            // Save the image file to the directory on the server
            imageFile.transferTo(new File(absoluteImagePath, filename));

            // Create a new Plant object with the image file path
            Plant plant = new Plant();
            plant.setName(name);
            plant.setDescription(description);
            plant.setInstruction(instruction);
            plant.setImage("http://localhost:8080/images/" + filename);
            plant.setDate(date);

            // Save the Plant entity to the database
            Plant addedPlant = plantService.addPlant(plant);

            // You can return a success response or the saved plant object
            return ResponseEntity.ok(addedPlant);
        } catch (IOException e) {
            // Handle the exception (e.g., return an error response)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }

    @PutMapping("/{id}")
    public void updatePlant(
            @PathVariable Long id,
            @RequestBody Plant request) {
        plantService.updatePlant(id, request.getName(), request.getDescription(), request.getImage(),
                request.getInstruction());
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable Long id) {
        Plant plant = plantService.getPlantById(id);
        return ResponseEntity.ok(plant);
    }
}
