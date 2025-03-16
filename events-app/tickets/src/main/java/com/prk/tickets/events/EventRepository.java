package com.prk.tickets.events;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    private final List<Event> events = List.of(
        new Event(501, "Waste Management Annual Conference",
                new Organizer(101, "WM", "Waste Management"),
                new Venue(201, "PHX Arena", "201 E Jefferson St", "Phoenix", "USA"),
                LocalDate.of(2025, 10, 6),
                LocalDate.of(2025, 10, 8)),
        new Event(502, "Cintas Annual Shareholders Meeting",
                new Organizer(102, "CTAS", "Cintas Corporation"),
                new Venue(201, "PHX Arena", "201 E Jefferson St", "Phoenix", "USA"),
                LocalDate.of(2025, 9, 7),
                LocalDate.of(2025, 9, 8)),
        new Event (503, "Cintas Special Conference",
                new Organizer(102, "CTAS", "Cintas Corporation"),
                new Venue(202, "Mesa Convention Center", "263 N Center St", "Mesa", "USA"),
                LocalDate.of(2025, 10, 1),
                LocalDate.of(2025, 10, 4)));

    public List<Event> findByOrganizerId(int organizerId) {
        return events.stream()
                .filter(e -> e.organizer().id() == organizerId)
                .toList();
    }

    public Optional<Event> findById(int id) {
        return events.stream()
                .filter(e -> e.id() == id)
                .findAny();
    }
}
