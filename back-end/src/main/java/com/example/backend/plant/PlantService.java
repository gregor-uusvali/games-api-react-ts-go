package com.example.backend.plant;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlantService {
    public List<Plant> getPlants(){
        return List.of(
                new Plant(
                        1L,
                        "Schlumbergera",
                        "Schlumbergera is a small genus of cacti with six to nine species found in the coastal mountains of south-eastern Brazil. These plants grow on trees or rocks in habitats that are generally shady with high humidity, and can be quite different in appearance from their desert-dwelling cousins.",
                        "https://juhendaja.ee/wp-content/uploads/2014/10/120.jpg",
                        "Mist your plant a few times a week, or place on a pebble-filled tray of water. Feed monthly in spring or summer with a general fertiliser. Schlumbergera don't need pruning, but the stems can get leggy or too long. Make the plant more bushy by removing the tips after the plant has flowered.",
                        LocalDate.now()
                )
        );
    }
}
