package com.example.backend.plant;

import com.example.backend.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getPlants(){
//        Plant plant1 = new Plant(
//                1L,
//                "Schlumbergera",
//                "Schlumbergera is a small genus of cacti with six to nine species found in the coastal mountains of south-eastern Brazil. These plants grow on trees or rocks in habitats that are generally shady with high humidity, and can be quite different in appearance from their desert-dwelling cousins.",
//                "https://juhendaja.ee/wp-content/uploads/2014/10/120.jpg",
//                "Mist your plant a few times a week, or place on a pebble-filled tray of water. Feed monthly in spring or summer with a general fertiliser. Schlumbergera don't need pruning, but the stems can get leggy or too long. Make the plant more bushy by removing the tips after the plant has flowered.",
//                LocalDate.of(2023, Month.SEPTEMBER, 12)
//        );
//        Plant plant2 = new Plant(
//                2L,
//                "Dracaena",
//                "Dracaena is a genus of about 120 species of trees and succulent shrubs. The formerly accepted genera Pleomele and Sansevieria are now included in Dracaena. In the APG IV classification system, it is placed in the family Asparagaceae, subfamily Nolinoideae.",
//                "https://f8.pmo.ee/e7U3TOWk7zGLAqNYixGGq7dqgXo=/685x0/nginx/o/2011/09/07/739442t1h3146.jpg",
//                "Your Dracaena likes comfortable room temperatures between 65-80°F. The Dracaena is slow-growing and does not need much fertilizer. Feed once a month in the spring and summer with an all-purpose plant food, diluted to half-strength. No fertilizer is necessary during the fall and winter when plant growth naturally slows.",
//                LocalDate.of(2023, Month.SEPTEMBER, 12)
//        );
//        plantRepository.saveAll(List.of(plant1, plant2));
        return plantRepository.findAll();
    }

    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }
}
