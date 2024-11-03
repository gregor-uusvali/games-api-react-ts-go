package com.example.backend.dailyRandom;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyRandomService {
    private final  DailyRandomRepository dailyRandomRepository;

    public DailyRandomService(DailyRandomRepository dailyRandomRepository) {
        this.dailyRandomRepository = dailyRandomRepository;
    }

    public int getDailyRandomNumber() {
        List<DailyRandom> drList = this.dailyRandomRepository.findAll();

        DailyRandom dr = new DailyRandom();

        if (drList.size() == 0 || drList == null){
            dr = saveNewDate(dr);
        } else {
            if (DateUtils.isSameDay(drList.get(0).today,new Date())) {
                dr = drList.get(0);
            } else {
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
}
