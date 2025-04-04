package com.prk.flights.domian;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "FlightPlans")
public class FlightPlan {
    @Id
    private String id;

    @Field(name = "departure")
    private String departureCity;

    @Field(name = "destination")
    private String destinationCity;

    @Indexed(direction = IndexDirection.ASCENDING)
    private LocalDateTime departureDateTime;

    private int flightDuration;
    private List<String> crossedCountries;
    private boolean isInternational;
    private Aircraft aircraft;

    public FlightPlan (String departureCity,
                       String destinationCity,
                       LocalDateTime departureDateTime,
                       int flightDuration,
                       List<String> crossedCountries,
                       boolean isInternational,
                       Aircraft aircraft) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureDateTime = departureDateTime;
        this.flightDuration = flightDuration;
        this.crossedCountries = crossedCountries;
        this.isInternational = isInternational;
        this.aircraft = aircraft;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(int flightDuration) {
        this.flightDuration = flightDuration;
    }

    public List<String> getCrossedCountries() {
        return crossedCountries;
    }

    public void setCrossedCountries(List<String> crossedCountries) {
        this.crossedCountries = crossedCountries;
    }

    public boolean isInternational() {
        return isInternational;
    }

    public void setInternational(boolean international) {
        isInternational = international;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public String toString() {
        return "FlightPlan{" +
                "id='" + id + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", flightDuration=" + flightDuration + " minutes" +
                ", crossedCountries=" + crossedCountries +
                ", isInternational=" + isInternational +
                ", aircraft=" + (aircraft != null ? aircraft.toString() : "None") +
                '}';
    }
}
