package uk.gov.dwp.uc.pairtest.service;

/*
- Calculates the correct amount for the requested tickets and makes a payment request to the `TicketPaymentService`.
 */

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface CalculateMoney {
    int toPay(TicketTypeRequest... ticketTypeRequests);
}
