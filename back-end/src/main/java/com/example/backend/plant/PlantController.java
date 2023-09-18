package com.example.backend.plant;

import com.example.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Plant> getPlants(){
        return plantService.getPlants();
    }

    @PostMapping(path="/add")
    public ResponseEntity<?> addPlant(@RequestBody Plant plant) {
        Plant addedPlant = plantService.addPlant(plant);

        // You can return a success response or the saved user object
        return ResponseEntity.ok(addedPlant);
    }
    @PutMapping("/{id}")
    public void updatePlant(
            @PathVariable Long id,
            @RequestBody Plant request
    ) {
        plantService.updatePlant(id, request.getName(), request.getDescription(), request.getImage(), request.getInstruction());
    }
    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id){
        plantService.deletePlant(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlant(@PathVariable Long id){
        Plant plant = plantService.getPlantById(id);
        return ResponseEntity.ok(plant);
    }
}
