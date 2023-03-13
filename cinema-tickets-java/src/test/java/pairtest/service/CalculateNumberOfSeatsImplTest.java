package pairtest.service;

import org.junit.Before;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.service.CalculateNumberOfSeats;
import uk.gov.dwp.uc.pairtest.service.CalculateNumberOfSeatsImpl;

import static org.junit.Assert.assertEquals;

public class CalculateNumberOfSeatsImplTest {

    private CalculateNumberOfSeats calculateNumberOfSeats;

    @Before
    public void setUp() {
        this.calculateNumberOfSeats = new CalculateNumberOfSeatsImpl();
    }

    @Test
    public void calculateAmountToPayForMixedTickets() {

        var twoInfantTickets = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
        var threeChildTickets = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        var fourAdultTickets = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 4);

        var result = this.calculateNumberOfSeats.toReserve(fourAdultTickets, threeChildTickets, twoInfantTickets);

        assertEquals(7, result);
    }
}