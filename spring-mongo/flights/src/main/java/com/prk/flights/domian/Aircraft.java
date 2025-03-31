package com.prk.flights.domian;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Aircraft {
    private String model;

    @Field(name = "capacity")
    private int seatCapacity;

    private WakeTurbulence wakeTurbulence;

    public Aircraft (String model, int seatCapacity, WakeTurbulence wakeTurbulence) {
        this.model = model;
        this.seatCapacity = seatCapacity;
        this.wakeTurbulence = wakeTurbulence;
    }

    public String getModel() { return model; }

    public int getSeatCapacity() { return seatCapacity; }

    public WakeTurbulence getWakeTurbulence() { return wakeTurbulence; }

    @Override
    public String toString() {
        return "Aircraft {" + "model='" + model + '\'' + ", seats=" + seatCapacity +
                ", turbulence=" + wakeTurbulence + "}";
    }
}