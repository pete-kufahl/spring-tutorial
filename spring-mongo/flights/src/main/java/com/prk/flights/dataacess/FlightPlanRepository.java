package com.prk.flights.dataacess;

import com.prk.flights.domian.FlightPlan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface FlightPlanRepository extends MongoRepository<FlightPlan, String> {

    List<FlightPlan> findFlightPlansByIsInternationalTrueAndCrossedCountriesContaining(String country);

    @Query("{'isInternational' : true, 'crossedCountries' : { '$in' : [?0]}}")
    List<FlightPlan> findInternationalCrossing(String country);

    List<FlightPlan> findFlightPlansByFlightDurationBetween(int minDuration, int maxDuration, PageRequest pageRequest);

    List<FlightPlan> findFlightPlansByAircraftModelContainsOrderByAircraftSeatCapacityAsc(String aircraft);

    List<FlightPlan> findAllBy(TextCriteria criteria);

    @Update("{ '$inc' : { 'flightDuration' : ?1 } }")
    void findAndChangeDurationByDepartureCityContains(String departure, int nbMinutes);

    void deleteAllByDepartureCityContains(String departureCity);

}
