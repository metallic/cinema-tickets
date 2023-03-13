package pairtest;

import org.junit.Before;
import org.junit.Test;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.service.*;

import static org.mockito.Mockito.*;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class TicketServiceImplTest {
    private SeatReservationService mockedSeatReservationService;
    private TicketPaymentService mockedTicketPaymentService;
    private CalculateMoney calculateMoney;
    private CalculateNumberOfSeats calculateNumberOfSeats;
    private ValidateTicketPurchaseRequest validateTicketPurchaseRequest;
    private TicketServiceImpl ticketService;

    @Before
    public void setUp() {
        this.mockedSeatReservationService = mock(SeatReservationService.class);
        this.mockedTicketPaymentService = mock(TicketPaymentService.class);

        this.calculateMoney = new CalculateMoneyImpl();
        this.calculateNumberOfSeats = new CalculateNumberOfSeatsImpl();
        this.validateTicketPurchaseRequest = new ValidateTicketPurchaseRequestImpl();

        this.ticketService = new TicketServiceImpl(mockedSeatReservationService, mockedTicketPaymentService,
                calculateMoney, calculateNumberOfSeats, validateTicketPurchaseRequest);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void throwsInvalidPurchaseExceptionWhenAccountIdIsInvalid() {
        var fourAdultTickets = new TicketTypeRequest(Type.ADULT, 4);

        ticketService.purchaseTickets(-1L, fourAdultTickets);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void throwsInvalidPurchaseExceptionWhenInvalidTicketsNumber() {
        var twentyFiveAdultTickets = new TicketTypeRequest(Type.ADULT, 25);

        ticketService.purchaseTickets(1L, twentyFiveAdultTickets);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void throwsInvalidPurchaseExceptionWhenNoAdultTicket() {
        var twoInfantTickets = new TicketTypeRequest(Type.INFANT, 2);
        var threeChildTickets = new TicketTypeRequest(Type.CHILD, 3);

        ticketService.purchaseTickets(1L, twoInfantTickets, threeChildTickets);
    }

    @Test
    public void purchaseTicketsTest() {
        var twoInfantTickets = new TicketTypeRequest(Type.INFANT, 2);
        var threeChildTickets = new TicketTypeRequest(Type.CHILD, 3);
        var fourAdultTickets = new TicketTypeRequest(Type.ADULT, 4);

        this.ticketService.purchaseTickets(10L, twoInfantTickets, threeChildTickets, fourAdultTickets);

        verify(mockedTicketPaymentService, times(1)).makePayment(10, 110);
    }
}