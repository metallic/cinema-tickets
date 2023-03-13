package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

public class CalculateNumberOfSeatsImpl implements CalculateNumberOfSeats {
    @Override
    public int toReserve(TicketTypeRequest... ticketTypeRequests) {

        return Arrays.stream(ticketTypeRequests)
                .filter( ticket -> ( ticket.getTicketType() != TicketTypeRequest.Type.INFANT))
                .mapToInt( TicketTypeRequest::getNoOfTickets)
                .sum();
    }
}
