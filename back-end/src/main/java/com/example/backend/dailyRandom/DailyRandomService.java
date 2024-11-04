package com.example.backend.dailyRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@Service
public class DailyRandomService {

    // Autowire the Environment object for accessing environment variables
    @Autowired
    private Environment environment;


    private final  DailyRandomRepository dailyRandomRepository;

    public DailyRandomService(DailyRandomRepository dailyRandomRepository) {
        this.dailyRandomRepository = dailyRandomRepository;
    }

    public int getDailyRandomNumber() {
        List<DailyRandom> drList = this.dailyRandomRepository.findAll();

        DailyRandom dr = new DailyRandom();
        System.out.println(drList);

        if (drList.size() == 0 || drList == null){
            dr = saveNewDate(dr);
        } else {
            if (DateUtils.isSameDay(drList.get(0).today,new Date())) {
                dr = drList.get(0);
            } else {
                this.dailyRandomRepository.deleteAll();
                dr = saveNewDate(dr);
            }
        }
        return dr.number;
    }

    private DailyRandom saveNewDate(DailyRandom dr) {
        dr.setNumber((int) (Math.random() * 400 * 1000));
        dr.setToday(new Date());
        return dailyRandomRepository.save(dr);
    }

    public String getDailyRandomPlant(int rn) throws URISyntaxException, IOException, InterruptedException {
        String trefoilApiKey = environment.getProperty("treffleApiKey");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://trefle.io/api/v1/plants/" + rn + "?token=" + trefoilApiKey))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
