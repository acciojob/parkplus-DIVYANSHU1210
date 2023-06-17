package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ParkingLot newparkinglot = new ParkingLot(name, address);
        parkingLotRepository1.save(newparkinglot);
        return newparkinglot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) throws Exception {
        Optional<ParkingLot> parkingLotOP = parkingLotRepository1.findById(parkingLotId);

        if(!parkingLotOP.isPresent()){
            throw new Exception("ParkingLot not found");
        }
        SpotType spotType;
        if(numberOfWheels == 4)spotType = SpotType.valueOf("FOUR_WHEELER");
        else if(numberOfWheels == 2)spotType = SpotType.valueOf("TWO_WHEELER");
        else spotType = SpotType.valueOf("OTHERS");

        ParkingLot parkingLot = parkingLotOP.get();
        Spot spot = new Spot(spotType, pricePerHour, parkingLot);
        spotRepository1.save(spot);
        return spot;

    }

    @Override
    public void deleteSpot(int spotId) throws Exception {
        Optional<Spot> spotOptional = spotRepository1.findById(spotId);

        if(!spotOptional.isPresent()){
            throw new Exception("SpotId not Found");
        }

        Spot spot = spotOptional.get();

        spotRepository1.delete(spot);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) throws Exception {
        Optional<Spot> spotOptional = spotRepository1.findById(spotId);

        if(!spotOptional.isPresent()){
            throw new Exception("SpotId not Found");
        }
        Spot spot = spotOptional.get();


        Optional<ParkingLot> parkingLotOP = parkingLotRepository1.findById(parkingLotId);

        if(!parkingLotOP.isPresent()){
            throw new Exception("ParkingLot not found");
        }

        ParkingLot parkingLot = parkingLotOP.get();

        spot.setPricePerHour(pricePerHour);
        spotRepository1.save(spot);



        List<Spot> spotlist = parkingLot.getSpotList();
        for(int j=0; j<spotlist.size(); j++){
            if(spotlist.get(j).getId() == spot.getId()){
                spotlist.remove(j);
            }
        }
        spotlist.add(spot);
        parkingLot.setSpotList(spotlist);
        parkingLotRepository1.save(parkingLot);
    }

    @Override
    public void deleteParkingLot(int parkingLotId) throws Exception {
        Optional<ParkingLot> parkingLotOP = parkingLotRepository1.findById(parkingLotId);

        if(!parkingLotOP.isPresent()){
            throw new Exception("ParkingLot not found");
        }

        ParkingLot parkingLot = parkingLotOP.get();

        List<Spot> spotList = parkingLot.getSpotList();

        for (Spot spot : spotList) {
            spotRepository1.delete(spot);
        }

        parkingLotRepository1.delete(parkingLot);
    }
}
