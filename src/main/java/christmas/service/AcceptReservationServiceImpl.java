package christmas.service;

import christmas.domain.reservation.EventReservation;
import christmas.domain.reservation.Order;
import christmas.domain.reservation.Reservation;
import christmas.repository.SingleRepository;
import christmas.utils.InputParser;
import java.time.LocalDate;
import java.util.List;

public class AcceptReservationServiceImpl implements AcceptReservationService {
    private final SingleRepository<Reservation> reservationRepository;
    private final InputParser<List<Order>> ordersParser;
    private final InputParser<LocalDate> dateToVisitParser;
    private LocalDate dateToVisit;
    private List<Order> orders;

    public AcceptReservationServiceImpl(SingleRepository<Reservation>  reservationRepository,
                                        InputParser<List<Order>> ordersParser,
                                        InputParser<LocalDate> dateToVisitParser) {
        this.reservationRepository = reservationRepository;
        this.ordersParser = ordersParser;
        this.dateToVisitParser = dateToVisitParser;
    }

    @Override
    public void buildDateToVisit(String rawDateToVisit) {
        dateToVisit =  dateToVisitParser.parse(rawDateToVisit);
    }

    @Override
    public void buildOrders(String rawOrders) {
        orders =  ordersParser.parse(rawOrders);
    }

    @Override
    public void buildReservations() {
        Reservation reservation = EventReservation.create(orders,dateToVisit);
        reservationRepository.save(reservation);
    }
}
