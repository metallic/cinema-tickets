package uk.gov.dwp.uc.pairtest.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class CalculateMoneyImpl implements CalculateMoney {
    /*
     * Three ticket types:
     *      Infant £0
     *      Child £10
     *      Adult £20
     */
    private static int INFANT_TICKET_PRICE = 0;
    private static int CHILD_TICKET_PRICE = 10;
    private static int ADULT_TICKET_PRICE = 20;

    @Override
    public int toPay(TicketTypeRequest... ticketTypeRequests) {
        int retVal =  0;


        for (TicketTypeRequest item : ticketTypeRequests) {
            switch (item.getTicketType()) {
                case INFANT:
                    retVal += (item.getNoOfTickets() * INFANT_TICKET_PRICE);
                    break;

                case CHILD:
                    retVal += (item.getNoOfTickets() * CHILD_TICKET_PRICE);
                    break;

                case ADULT:
                    retVal += (item.getNoOfTickets() * ADULT_TICKET_PRICE);
                    break;

                default:
                    break;
            }
        }

        return retVal;
    }
}
