package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Table(name = "Spots")
@Entity
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    private int pricePerHour;

    private boolean Occupied;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @ManyToOne
    private ParkingLot parkingLot;











    public Spot() {
    }

    public Spot(int id, SpotType spotType, int pricePerHour, boolean occupied, List<Reservation> reservationList,  ParkingLot parkingLot) {
        this.id = id;
        this.spotType = spotType;
        this.pricePerHour = pricePerHour;
        this.Occupied = occupied;
        this.reservationList = reservationList;
        this.parkingLot = parkingLot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public boolean getOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean occupied) {
        Occupied = occupied;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
