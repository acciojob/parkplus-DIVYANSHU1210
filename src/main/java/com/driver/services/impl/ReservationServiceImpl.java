package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        Optional<User> userOptional = userRepository3.findById(userId);
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository3.findById(parkingLotId);

        if(!userOptional.isPresent()){
            throw new Exception("User not found");
        }

        if(!parkingLotOptional.isPresent()){
            throw new Exception("Parking Lot not found");
        }

        User user = userOptional.get();
        ParkingLot parkingLot = parkingLotOptional.get();

        SpotType spotType;

        if(numberOfWheels == 4)spotType = SpotType.valueOf("FOUR_WHEELER");

        else if(numberOfWheels == 2)spotType = SpotType.valueOf("TWO_WHEELER");

        else spotType = SpotType.valueOf("OTHERS");



        int minprice = Integer.MAX_VALUE;
        Spot bestspot = new Spot();
        boolean found = false;
        int position = 0;
        for(int i= 0; i<parkingLot.getSpotList().size(); i++){
            Spot spot = parkingLot.getSpotList().get(i);
            if(!spot.isOccupied() && spot.getSpotType() == spotType && spot.getPricePerHour() < minprice){
                found = true;
                position = i;
                minprice = spot.getPricePerHour();
                bestspot = spot;
            }
        }

        if(!found) throw new Exception("Spot is not available");

        parkingLot.getSpotList().get(position).setOccupied(true);

        parkingLotRepository3.save(parkingLot);

        Payment newPayment = new Payment();
        Reservation reservation = new Reservation();

        return reservation;

    }
}
