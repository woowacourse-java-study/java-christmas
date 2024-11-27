package christmas.utils.impl;

import static christmas.exception.ErrorMessage.NOT_INTEGER;
import static christmas.exception.ErrorMessage.NOT_VALID_DATE;

import christmas.utils.InputValidator;

public class DateToVisitValidator implements InputValidator {
    private final int FIRST_DATE = 1;
    private final int LAST_DATE = 31;

    @Override
    public void validate(String input) {
        validateInteger(input);
        validateInRange(input);
    }

    private void validateInteger(String date) {
        try{
            Integer.parseInt(date);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER.getErrorMessage());
        }
    }

    private void validateInRange(String date) {
        int parsedDate = Integer.parseInt(date);
        if (parsedDate < FIRST_DATE || parsedDate > LAST_DATE) {
            throw new IllegalArgumentException(NOT_VALID_DATE.getErrorMessage());
        }
    }
}
