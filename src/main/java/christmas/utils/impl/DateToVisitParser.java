package christmas.utils.impl;

import christmas.utils.InputParser;
import christmas.utils.InputValidator;
import java.time.LocalDate;

public class DateToVisitParser implements InputParser<LocalDate> {
    private final InputValidator dateToVisitValidator;

    public DateToVisitParser(InputValidator dateToVisitValidator) {
        this.dateToVisitValidator = dateToVisitValidator;
    }

    @Override
    public LocalDate parse(String rawDate) {
        dateToVisitValidator.validate(rawDate);
        int day =  Integer.parseInt(rawDate);
        return LocalDate.of(2023, 12, day);
    }
}
