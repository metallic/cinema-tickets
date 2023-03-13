package uk.gov.dwp.uc.pairtest.service;

/*
- Calculates the correct no of seats to reserve and makes a seat reservation request to the `SeatReservationService`.
 */

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface CalculateNumberOfSeats {
    int toReserve(TicketTypeRequest... ticketTypeRequests);
}
