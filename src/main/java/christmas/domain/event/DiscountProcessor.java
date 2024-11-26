package christmas.domain.event;

public interface DiscountProcessor<Type> {
    int processDiscount(Type input);
}
