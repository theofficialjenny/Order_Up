package com.example.orderup.menu_object;

import java.util.Date;

public class Reservation {
    private String reservationID;
    private String reservationUser;
    private String reservationTableNumber;
    private Date reservationDate;
    private Type reservationType;
    private Status reservationStatus;

    public Reservation(String reservationUser, String reservationTableNumber, Date reservationDate, Type reservationType, Status reservationStatus) {
        this.reservationUser = reservationUser;
        this.reservationTableNumber = reservationTableNumber;
        this.reservationDate = reservationDate;
        this.reservationType = reservationType;
        this.reservationStatus = reservationStatus;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public String getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(String reservationUser) {
        this.reservationUser = reservationUser;
    }

    public String getReservationTableNumber() {
        return reservationTableNumber;
    }

    public void setReservationTableNumber(String reservationTableNumber) {
        this.reservationTableNumber = reservationTableNumber;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Type getReservationType() {
        return reservationType;
    }

    public void setReservationType(Type reservationType) {
        this.reservationType = reservationType;
    }

    public Status getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(Status reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID='" + reservationID + '\'' +
                ", reservationUser='" + reservationUser + '\'' +
                ", reservationTableNumber='" + reservationTableNumber + '\'' +
                ", reservationDate=" + reservationDate +
                ", reservationType=" + reservationType +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
