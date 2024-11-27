package christmas.utils.impl;

import static christmas.exception.ErrorMessage.NOT_VALID_ORDER_FORMAT;

import christmas.domain.menu.impl.Menu;
import christmas.utils.InputValidator;
import java.util.HashSet;
import java.util.Set;

public class OrdersValidator implements InputValidator {
    @Override
    public void validate(String rawOrders) {
        validateNotEmpty(rawOrders);
        validateFormat(rawOrders);

    }

    private void validateNotEmpty(String rawOrders) {
        if (rawOrders == null || rawOrders.isEmpty()) {
            throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
        }
    }

    private void validateFormat(String rawOrders) {
        Set<String> dishes = new HashSet<>();

        for (String rawOrder : rawOrders.split(",")) {
            String[] parts = rawOrder.split("-");

            validateDishName(parts[0]);
            validateQuantity(parts[1]);

            String dishName = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            if (dishes.contains(dishName)) {
                throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
            }
            dishes.add(dishName);
        }
    }

    private void validateDishName(String dishName) {
        if (dishName == null || dishName.isEmpty()) {
            throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
        }
        try {Menu.valueOf(dishName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
        }
    }

    private void validateQuantity(String rawQuantity) {
        if (rawQuantity == null || rawQuantity.isEmpty()) {
            throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
        }
        try{
            int quantity = Integer.parseInt(rawQuantity);
            if(quantity < 1){
                throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_VALID_ORDER_FORMAT.getErrorMessage());
        }
    }
}
