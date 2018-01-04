package com.example.ruben.hoteissines.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruben on 03/01/2018.
 */

public class HoteisData {

    private static List<Hotel> hotels;

    public static List<Hotel> getHotels() {
        if (hotels == null) {
            hotels = new ArrayList<>();
        }
        return hotels;
    }

    public static void setHotels(List<Hotel> hoteis) {
        hotels = hoteis;
    }
}
