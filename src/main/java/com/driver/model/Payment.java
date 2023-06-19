package com.driver.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
@Table(name = "payments")
@Entity
public class Payment {
    @Id
    private int id;

    private Boolean paymentCompleted;


    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;


    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Reservation reservation;











    public Payment() {
    }

    public Payment(int id, Boolean paymentCompleted, PaymentMode paymentMode, Reservation reservation) {
        this.id = id;
        this.paymentCompleted = paymentCompleted;
        this.paymentMode = paymentMode;
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getPaymentCompleted() {
        return paymentCompleted;
    }

    public void isPaymentCompleted(Boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
