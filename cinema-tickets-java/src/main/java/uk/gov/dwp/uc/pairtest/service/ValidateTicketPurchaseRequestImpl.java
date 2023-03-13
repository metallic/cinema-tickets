package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

public class ValidateTicketPurchaseRequestImpl implements ValidateTicketPurchaseRequest {
    private static final Long VALID_ACCOUNT_ID_VALUE = 1L;
    private static final int ALLOWED_TICKETS_NUMBER = 20;

    @Override
    public boolean isValid(Long accountId, TicketTypeRequest... ticketTypeRequests) {

        boolean retVal = false;

        if (accountId >= VALID_ACCOUNT_ID_VALUE &&
                getNumberOfTickets(ticketTypeRequests) <= ALLOWED_TICKETS_NUMBER &&
                    isValidNumberOfAdultTicketsForChildrenAndInfants(ticketTypeRequests)) {
            retVal = true;
        }

        return retVal;
    }

    private int getNumberOfTickets(TicketTypeRequest... ticketTypeRequests) {

        return Arrays.stream(ticketTypeRequests)
                .mapToInt(TicketTypeRequest::getNoOfTickets)
                .sum();
    }

    private boolean isValidNumberOfAdultTicketsForChildrenAndInfants(TicketTypeRequest... ticketTypeRequests) {
        /*
        This implementation assumes 1 Adult ticket is required for ANY number of Child and Infant tickets
         */

       return Arrays.stream(ticketTypeRequests)
                .anyMatch( ticket -> ( ticket.getTicketType() == TicketTypeRequest.Type.ADULT));
    }
}
