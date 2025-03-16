package com.prk.tickets.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Registration(
        Integer id,
        @NotNull(message = "productId is required") Integer productId,
        String ticketCode,
        @NotBlank(message = "attendeeName is required") String attendeeName) {
}
