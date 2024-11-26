package christmas.domain.reservation;

public class EventOrder implements Order {
    private final String dishName;
    private final int count;

    public EventOrder(String dishName, int count) {
        this.dishName = dishName;
        this.count = count;
    }

    public int getCount(){
        return count;
    }
    public String getDishName() {
        return dishName;
    }
}
