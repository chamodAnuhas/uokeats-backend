package com.campus.smart_campus_backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Frontend (HTML/JS) request
public class CanteenController {

    @GetMapping("/canteens")
    public List<Canteen> getCanteens() {
        List<Canteen> canteensList = new ArrayList<>();
        // Canteen class  constructor

        canteensList.add(new Canteen("Hilton Canteen", "Medium", true, 6.9760629, 79.9156555));
        canteensList.add(new Canteen("Gym Canteen", "Low", true, 6.9742245, 79.9166772));
        canteensList.add(new Canteen("Staff Canteen", "Low", true, 6.9754761, 79.9155129));
        return canteensList;
    }
}