package com.prk.registration;

import com.prk.events.Event;
import com.prk.events.EventsClient;
import com.prk.events.Product;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/registrations")
public class RegistrationController {

    private final EventsClient eventsClient;
    private final RegistrationRepository registrationRepository;

    public RegistrationController(EventsClient eventsClient, RegistrationRepository registrationRepository) {
        this.eventsClient = eventsClient;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public Registration create(@RequestBody Registration registration) {
        System.out.println("POST registrations called with: " + registration);
        Product product = eventsClient.getProductById(registration.productId());
        Event event = eventsClient.getEventById(product.eventId());
        String ticketCode = UUID.randomUUID().toString();
        return registrationRepository.save(new Registration(
                null,   // supplied by mongo
                registration.productId(),
                event.name(),
                product.price(),
                ticketCode,
                registration.attendeeName()));
    }

    @GetMapping(path = "/{ticketCode}")
    public Registration get(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("registration by ticket code " + ticketCode + " not found"));
    }

    @PutMapping
    public Registration update(@RequestBody Registration registration) {
        // lookup existing registration by ticket-code
        String ticketCode = registration.ticketCode();
        var existing = registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("registration with ticket code " + ticketCode + " not found"));
        // save, only update attendee name
        return registrationRepository.save(new Registration(
                existing.id(),
                existing.productId(),
                existing.eventName(),
                existing.amount(),
                ticketCode,
                registration.attendeeName()));
    }

    @DeleteMapping(path = "/{ticketCode}")
    public void delete(@PathVariable("ticketCode") String ticketCode) {
        registrationRepository.deleteByTicketCode(ticketCode);
    }
}
