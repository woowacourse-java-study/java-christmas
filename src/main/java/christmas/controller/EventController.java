package christmas.controller;

import christmas.dto.ReceiptDTO;
import christmas.service.AcceptReservationService;
import christmas.service.ProcessEventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final AcceptReservationService acceptReservationService;
    private final ProcessEventService processEventService;

    public EventController(InputView inputView,
                           OutputView outputView,
                           AcceptReservationService acceptReservationService,
                           ProcessEventService processEventService) {

        this.inputView = inputView;
        this.outputView = outputView;
        this.acceptReservationService = acceptReservationService;
        this.processEventService = processEventService;
    }

    public void run(){
        outputView.printWelcomeMessage();

        acceptReservationService.buildDateToVisit(inputView.readDate());
        acceptReservationService.buildOrders(inputView.readOrders());
        acceptReservationService.buildReservations();

        ReceiptDTO receiptDTO =  processEventService.sendReceipt();
        outputView.printReceipt(receiptDTO);
    }
}
