package com.driver.services;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;

public interface ParkingLotService {
    
    void deleteSpot(int spotId) throws Exception;

    Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) throws Exception;

    void deleteParkingLot(int parkingLotId) throws Exception;

    ParkingLot addParkingLot(String name, String address) throws Exception;

    Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) throws Exception;
}
