package uk.gov.dwp.uc.pairtest.service;

/*
- Rejects any invalid ticket purchase requests. It is up to you to identify what should be deemed as an invalid purchase request.
 */

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public interface ValidateTicketPurchaseRequest {
    boolean isValid(Long accountId, TicketTypeRequest... ticketTypeRequests);
}
