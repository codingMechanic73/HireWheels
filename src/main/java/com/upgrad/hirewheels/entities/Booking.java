package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(nullable = false)
    private Date pickupDate;

    @Column(nullable = false)
    private Date dropoffDate;

    @Column(nullable = false)
    private Date bookingDate;

    @Column(nullable = false)
    private double amount;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(Date dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", pickupDate=" + pickupDate +
                ", dropoffDate=" + dropoffDate +
                ", bookingDate=" + bookingDate +
                ", amount=" + amount +
                '}';
    }
}