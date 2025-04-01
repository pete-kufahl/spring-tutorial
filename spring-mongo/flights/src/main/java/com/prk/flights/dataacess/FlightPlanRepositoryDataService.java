package com.prk.flights.dataacess;

import com.prk.flights.domian.AircraftFactory;
import com.prk.flights.domian.FlightPlan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightPlanRepositoryDataService implements FlightPlanDataService {
    private FlightPlanRepository repository;

    public FlightPlanRepositoryDataService(FlightPlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public FlightPlan findById(String id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<FlightPlan> findInternationalCrossingFrance() {
        return repository.findInternationalCrossing("France");
    }

    @Override
    public List<FlightPlan> findFirstTwoFlightsWhichLastBetweenOneAndThreeHours() {
        return repository.findFlightPlansByFlightDurationBetween(
                60,
                180,
                PageRequest.of(0, 2));
    }

    @Override
    public List<FlightPlan> findBoeingFlightsAndOrderBySeatCapacity() {
        return repository.findFlightPlansByAircraftModelContainsOrderByAircraftSeatCapacityAsc("Boeing");
    }

    @Override
    public List<FlightPlan> findByFullTextSearch(String value) {
        var textCriteria = TextCriteria.forDefaultLanguage().matching(value);
        return repository.findAllBy(textCriteria);
    }

    @Override
    public void changeDepartureTimeById(String id, LocalDateTime newDepartureTime) {
        var flightPlanOptional = this.repository.findById(id);

        if (flightPlanOptional.isEmpty()) {
            return;
        }

        var flightPlan = flightPlanOptional.get();
        flightPlan.setDepartureDateTime(newDepartureTime);
        repository.save(flightPlan);
    }

    @Override
    public void incrementDurationForFlightsInParis(int minutesToAdd) {
        repository.findAndChangeDurationByDepartureCityContains("Paris", minutesToAdd);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllFromParis() {
        repository.deleteAllByDepartureCityContains("Paris");
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void insertInitialFlightPlans() {

        List<FlightPlan> initialData = new ArrayList<>();

        var parisToLondon = new FlightPlan(
                "Paris",
                "London",
                LocalDateTime.of(2023, 6, 1, 20, 15),
                90,
                List.of("France", "England"),
                true,
                AircraftFactory.buildBoeing737());
        initialData.add(parisToLondon);

        // Insert a list of documents
        var parisToNice = new FlightPlan(
                "Paris, France",
                "Nice, France",
                LocalDateTime.of(2023, 7, 3, 9, 0),
                100,
                List.of("France"),
                false,
                AircraftFactory.buildEmbraerE175()
        );
        initialData.add(parisToNice);

        var istanbulToPhuket = new FlightPlan(
                "Istanbul, Turkey",
                "Phuket, Thailand",
                LocalDateTime.of(2023, 12, 15, 22, 50),
                600,
                List.of("Turkey", "Iran", "Pakistan", "India", "Thailand"),
                true,
                AircraftFactory.buildAirbusA350()
        );
        initialData.add(istanbulToPhuket);

        var istanbulToBucharest = new FlightPlan(
                "Istanbul, Turkey",
                "Bucharest, Romania",
                LocalDateTime.of(2023, 12, 15, 21, 30),
                600,
                List.of("Turkey", "Romania"),
                true,
                AircraftFactory.buildBoeing737()
        );
        initialData.add(istanbulToBucharest);

        var berlinToNewYork = new FlightPlan(
                "Berlin, Germany",
                "New York, United States",
                LocalDateTime.of(2023, 9, 5, 15, 0),
                420,
                List.of("Germany", "England", "United States"),
                true,
                AircraftFactory.buildBoeing747()
        );
        initialData.add(berlinToNewYork);

        var viennaToBucharest = new FlightPlan(
                "Vienna, Austria",
                "Bucharest, Romania",
                LocalDateTime.of(2023, 8, 1, 11, 30),
                75,
                List.of("Austria", "Hungary", "Romania"),
                true,
                AircraftFactory.buildBoeing737()
        );
        initialData.add(viennaToBucharest);
        repository.insert(initialData);
    }
}
