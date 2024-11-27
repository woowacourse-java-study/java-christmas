package christmas.domain.event;

public interface Gift<Type> {
    Type getGiftOrNot(int amount);
}
