package christmas.model;

import java.util.List;

public class Cart {
    List<Product> products;

    public Cart(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }
    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += Menu.valueOf(product.getName()).getPrice() * product.getQuantity();
        }
        return total;
    }
}
