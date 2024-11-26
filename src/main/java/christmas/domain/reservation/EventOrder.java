package christmas.domain.reservation;

public class EventOrder implements Order {
    private final String dishName;
    private final int quantity;

    public EventOrder(String dishName, int quantity) {
        this.dishName = dishName;
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }
    public String getDishName() {
        return dishName;
    }
}
