package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.service.CalculateMoney;
import uk.gov.dwp.uc.pairtest.service.CalculateNumberOfSeats;
import uk.gov.dwp.uc.pairtest.service.ValidateTicketPurchaseRequest;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private SeatReservationService seatReservationService;
    private TicketPaymentService ticketPaymentService;
    private CalculateMoney calculateMoney;
    private CalculateNumberOfSeats calculateNumberOfSeats;
    private ValidateTicketPurchaseRequest validateTicketPurchaseRequest;

    public TicketServiceImpl(SeatReservationService seatReservationService, TicketPaymentService ticketPaymentService,
                             CalculateMoney calculateMoney, CalculateNumberOfSeats calculateNumberOfSeats,
                             ValidateTicketPurchaseRequest validateTicketPurchaseRequest) {

        this.seatReservationService = seatReservationService;
        this.ticketPaymentService = ticketPaymentService;
        this.calculateMoney = calculateMoney;
        this.calculateNumberOfSeats = calculateNumberOfSeats;
        this.validateTicketPurchaseRequest = validateTicketPurchaseRequest;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if (!this.validateTicketPurchaseRequest.isValid(accountId, ticketTypeRequests)) {
            throw new InvalidPurchaseException();
        }

        this.ticketPaymentService.makePayment(accountId, this.calculateMoney.toPay(ticketTypeRequests));

        this.seatReservationService.reserveSeat(accountId, this.calculateNumberOfSeats.toReserve(ticketTypeRequests));

    }

}
