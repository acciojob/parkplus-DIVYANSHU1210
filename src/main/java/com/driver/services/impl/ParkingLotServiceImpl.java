package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot newparkinglot = new ParkingLot();
        newparkinglot.setName(name);
        newparkinglot.setAddress(address);

        parkingLotRepository1.save(newparkinglot);
        return newparkinglot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour){
        Spot spot = new Spot();
        if(numberOfWheels <= 2)spot.setSpotType(SpotType.TWO_WHEELER);
        else if(numberOfWheels <= 2)spot.setSpotType(SpotType.FOUR_WHEELER);
        else if(numberOfWheels > 4) spot.setSpotType(SpotType.OTHERS);

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        spot.setParkingLot(parkingLot);
        spot.setPricePerHour(pricePerHour);

        List<Spot> spotList = parkingLot.getSpotList();
        spotList.add(spot);
        parkingLot.setSpotList(spotList);
        parkingLotRepository1.save(parkingLot);
        return spot;

    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

//    @Override
//    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
//        ParkingLot parkingLot =  parkingLotRepository1.findById(parkingLotId).get();
//
//        Spot spot = spotRepository1.findById(spotId).get();
//
//        Spot updatedSpot = null;
//        List<Spot> spotlist = parkingLot.getSpotList();
//
//        if(spotlist.contains(spot)){
//            spot.setPricePerHour(pricePerHour);
//            updatedSpot = spotRepository1.save(spot);
////            spotlist me spot apne aap update ho jayega
//        }
//        parkingLot.setSpotList(spotlist);
//
//        parkingLotRepository1.save(parkingLot);
//
//
//        return updatedSpot;
//    }


    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        Spot spot=spotRepository1.findById(spotId).get();

        Spot updatedSpot=null;
        List<Spot> spotList= parkingLot.getSpotList();
        if(spotList.contains(spot)){
            spot.setPricePerHour(pricePerHour);
            updatedSpot=spotRepository1.save(spot);
        }
        parkingLot.setSpotList(spotList);
        parkingLotRepository1.save(parkingLot);
        return updatedSpot;}


    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
