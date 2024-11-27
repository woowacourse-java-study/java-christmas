package christmas.config;

import christmas.controller.EventController;
import christmas.domain.event.DiscountProcessor;
import christmas.domain.event.EventProcessor;
import christmas.domain.event.Gift;
import christmas.domain.event.impl.BadgeGift;
import christmas.domain.event.impl.ChampagneGift;
import christmas.domain.event.impl.DDayDiscount;
import christmas.domain.event.impl.SpecialDiscount;
import christmas.domain.event.impl.TotalEventProcessor;
import christmas.domain.event.impl.WeekdaysDiscount;
import christmas.domain.event.impl.WeekendDiscount;
import christmas.domain.reservation.Order;
import christmas.domain.reservation.Reservation;
import christmas.repository.ReservationRepository;
import christmas.repository.SingleRepository;
import christmas.service.AcceptReservationService;
import christmas.service.AcceptReservationServiceImpl;
import christmas.service.ProcessEventService;
import christmas.service.ProcessEventServiceImpl;
import christmas.utils.InputParser;
import christmas.utils.InputValidator;
import christmas.utils.impl.DateToVisitParser;
import christmas.utils.impl.DateToVisitValidator;
import christmas.utils.impl.OrdersParser;
import christmas.utils.impl.OrdersValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.impl.ConsoleInputView;
import christmas.view.impl.ConsoleOutputView;
import java.time.LocalDate;
import java.util.List;

public class AppConfig {

    private final static OutputView consoleOutputView = new ConsoleOutputView();
    private final static InputView consoleInputView = new ConsoleInputView();

    private final static SingleRepository<Reservation> reservationRepository = new ReservationRepository();

    private final static InputValidator dateToVisitValidator = new DateToVisitValidator();
    private final static InputValidator ordersValidator = new OrdersValidator();

    private final static InputParser<LocalDate> dateToVisitParser = new DateToVisitParser(dateToVisitValidator);
    private final static InputParser<List<Order>> ordersParser = new OrdersParser(ordersValidator);

    private final static DiscountProcessor<LocalDate> dDayDiscount = new DDayDiscount();
    private final static DiscountProcessor<LocalDate> specialDiscount = new SpecialDiscount();
    private final static DiscountProcessor<List<Order>> weekdaysDiscount = new WeekdaysDiscount();
    private final static DiscountProcessor<List<Order>> weekendDiscount = new WeekendDiscount();

    private final static Gift<String> badgeGift = new BadgeGift();
    private final static Gift<Boolean> champagneGift = new ChampagneGift();

    private final static EventProcessor totalEventProcessor = new TotalEventProcessor(dDayDiscount,
            specialDiscount,
            weekdaysDiscount,
            weekendDiscount,
            champagneGift
            ,badgeGift);

    private final static AcceptReservationService acceptReservationServiceImpl = new AcceptReservationServiceImpl(reservationRepository,
            ordersParser,
            dateToVisitParser);
    private final static ProcessEventService processEventService = new ProcessEventServiceImpl(reservationRepository, totalEventProcessor);

    private final static EventController eventController = new EventController(consoleInputView,
            consoleOutputView,
            acceptReservationServiceImpl,
            processEventService);

    public static EventController getEventController() {
        return eventController;
    }
}
