package christmas.utils.impl;

import christmas.utils.InputParser;
import christmas.utils.InputValidator;

public class DateToVisitParser implements InputParser<Integer> {
    private final InputValidator dateToVisitValidator;

    public DateToVisitParser(InputValidator dateToVisitValidator) {
        this.dateToVisitValidator = dateToVisitValidator;
    }

    @Override
    public Integer parse(String rawDate) {
        dateToVisitValidator.validate(rawDate);
        return Integer.parseInt(rawDate);
    }
}
