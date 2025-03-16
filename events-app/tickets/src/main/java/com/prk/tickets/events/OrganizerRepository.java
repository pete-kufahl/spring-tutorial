package com.prk.tickets.events;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizerRepository {

    private final List<Organizer> organizers = List.of(
            new Organizer(101, "WM", "Waste Management"),
            new Organizer(102, "CTAS", "Cintas Corporation")
    );

    public List<Organizer> findAll() {
        return organizers;
    }
}
