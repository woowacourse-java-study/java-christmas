package christmas.model;

import christmas.message.Messages;
import java.util.ArrayList;
import java.util.List;

public class Cashier {
    public Date dateCashier(String inputDate) {
        try {
            return new Date(Integer.parseInt(inputDate.trim()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Messages.DATE_ERROR.getErrorFormatedMessage());
        }
    }

    public Cart menuCashier(String inputMenu) {
        List<Product> products = new ArrayList<>();
        List<String> inputProducts = List.of(inputMenu.trim().split(",", -1));
        for (String inputProduct : inputProducts) {
            productParser(inputProduct, products);
        }
        orderMaxQuantityValidator(products);
        for (Product product : products) {
            existMenuValidator(product);
        }
        onlyDrinkValidator(products);
        return new Cart(products);
    }

    private static void onlyDrinkValidator(List<Product> products) {
        boolean onlyDrink = true;
        for (Product product : products) {
            if (!Menu.valueOf(product.getName()).getCategory().equals("음료")) {
                onlyDrink = false;
                break;
            }
        }
        if (onlyDrink) {
            throw new IllegalArgumentException(Messages.MENU_ERROR.getMessage());
        }
    }

    private static void orderMaxQuantityValidator(List<Product> products) {
        int quantityStack = 0;
        for (Product product : products) {
            quantityStack += product.getQuantity();
        }
        if (quantityStack > 20) {
            throw new IllegalArgumentException(Messages.MENU_ERROR.getMessage());
        }
    }

    private static void existMenuValidator(Product product) {
        try {
            Menu.valueOf(product.getName());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Messages.MENU_ERROR.getMessage());
        }
    }

    private void productParser(String inputProduct, List<Product> products) {
        try {
            List<String> product = List.of(inputProduct.trim().split("-", -1));
            String name = product.get(0);
            productValidator(products, name, product);
            products.add(new Product(name, Integer.parseInt(product.get(1))));
        } catch (Exception e) {
            throw new IllegalArgumentException(Messages.MENU_ERROR.getErrorFormatedMessage());
        }
    }

    private void productValidator(List<Product> products, String name, List<String> product) {
        nullValidator(name);
        productDuplicateValidator(name, products);
        nullValidator(product.get(1));
        quantityValidator(product.get(1));
    }

    private void nullValidator(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(Messages.MENU_ERROR.getErrorFormatedMessage());
        }
    }

    private void productDuplicateValidator(String productName, List<Product> products) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                throw new IllegalArgumentException(Messages.MENU_ERROR.getErrorFormatedMessage());
            }
        }
    }

    private void quantityValidator(String StringQuantity) {
        try {
            int quantity = Integer.parseInt(StringQuantity);
            if (quantity < 1) {
                throw new IllegalArgumentException(Messages.MENU_ERROR.getErrorFormatedMessage());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.MENU_ERROR.getErrorFormatedMessage());
        }
    }
}
