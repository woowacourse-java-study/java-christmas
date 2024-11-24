package christmas.model;

import christmas.message.Messages;

public class Date {
    private final int day;

    public Date(int day) {
        validator(day);
        this.day = day;
    }

    private void validator(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(Messages.DATE_ERROR.getErrorFormatedMessage());
        }
    }
    public int getDay() {
        return day;
    }
}
