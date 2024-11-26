package christmas.repository;

import christmas.domain.reservation.Reservation;
import java.util.Optional;

public class ReservationRepository implements SingleRepository<Reservation> {
    private Reservation reservation;

    @Override
    public Optional<Reservation> get() {
        return Optional.ofNullable(this.reservation);
    }

    @Override
    public void save(Reservation reservation) {
        this.reservation = reservation;
    }
}

