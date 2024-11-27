package christmas.service;

import christmas.domain.event.EventProcessor;
import christmas.domain.reservation.Reservation;
import christmas.dto.ReceiptDTO;
import christmas.repository.SingleRepository;

public class ProcessEventServiceImpl implements  ProcessEventService{
    private final SingleRepository<Reservation> reservationRepositoryImpl;
    private final EventProcessor totalEventProcessor;

    public ProcessEventServiceImpl(SingleRepository<Reservation> reservationRepositoryImpl,
                                   EventProcessor totalEventProcessor) {
        this.reservationRepositoryImpl = reservationRepositoryImpl;
        this.totalEventProcessor = totalEventProcessor;
    }


    public ReceiptDTO sendReceipt(){
        Reservation eventReservation =  reservationRepositoryImpl.get()
                .orElseThrow(() -> new RuntimeException("There is no event reservation"));

        return new ReceiptDTO(eventReservation.makeReceipt(totalEventProcessor));
    }
}
