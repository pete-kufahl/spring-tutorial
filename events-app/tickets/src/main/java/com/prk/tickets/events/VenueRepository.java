package com.prk.tickets.events;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VenueRepository {
    private final List<Venue> venues = List.of(
            new Venue(201, "PHX Arena", "201 E Jefferson St", "Phoenix", "USA"),
            new Venue(202, "Mesa Convention Center", "263 N Center St", "Mesa", "USA")
    );

    public Optional<Venue> findById(int id) {
        return venues.stream()
                .filter(v -> v.id() == id)
                .findAny();
    }
}
