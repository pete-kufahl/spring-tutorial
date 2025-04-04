package com.prk.flights.dataacess;

import com.prk.flights.domian.FlightPlan;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightPlanDataService {
    FlightPlan findById(String id);

    List<FlightPlan> findInternationalCrossingFrance();

    List<FlightPlan> findFirstTwoFlightsWhichLastBetweenOneAndThreeHours();

    List<FlightPlan> findBoeingFlightsAndOrderBySeatCapacity();

    List<FlightPlan> findByFullTextSearch(String value);

    void changeDepartureTimeById(String id, LocalDateTime newDepartureTime);

    void incrementDurationForFlightsInParis(int minutesToAdd);

    void deleteById(String id);

    void deleteAllFromParis();

    void deleteAll();

    void insertInitialFlightPlans();
}
