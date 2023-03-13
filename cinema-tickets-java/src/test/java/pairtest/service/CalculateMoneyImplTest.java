package pairtest.service;

import org.junit.Before;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.service.CalculateMoney;
import uk.gov.dwp.uc.pairtest.service.CalculateMoneyImpl;

import static org.junit.Assert.assertEquals;

public class CalculateMoneyImplTest {

    private CalculateMoney calculateMoney;

    @Before
    public void setUp() {
        this.calculateMoney = new CalculateMoneyImpl();
    }

    @Test
    public void calculateAmountToPayForMixedTickets() {
        /*
         * Three ticket types:
         *      Infant £0
         *      Child £10
         *      Adult £20
         */

        var twoInfantTickets = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
        var threeChildTickets = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        var fourAdultTickets = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 4);

        var result = this.calculateMoney.toPay(fourAdultTickets, threeChildTickets, twoInfantTickets);

        assertEquals(110, result);
    }
}