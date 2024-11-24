package christmas.controller;

import christmas.model.Cart;
import christmas.model.Cashier;
import christmas.model.Date;
import christmas.model.Order;
import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Cashier cashier;
    private final Order order;


    public ChristmasController(InputView inputView, OutputView outputView, Cashier cashier, Order order) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cashier = cashier;
        this.order = order;
    }

    public void run() {
        outputView.responseWelcome();
        Date date = getDate();
        Cart cart = getMenu();
        Receipt receipt = new Receipt();
        order.eventApply(date, cart, receipt);
        outputView.responseReceiptInto(date);
        outputView.responseReceipt(cart, receipt);
    }

    private Date getDate() {
        while (true) {
            try {
                outputView.responseDateIntro();
                return cashier.dateCashier(inputView.requestDate());
            } catch (IllegalArgumentException e) {
                outputView.responseErrorMessage(e.getMessage());
            }
        }
    }

    private Cart getMenu() {
        while (true) {
            try {
                outputView.responseMenuIntro();
                return cashier.menuCashier(inputView.requestMenu());

            } catch (IllegalArgumentException e) {
                outputView.responseErrorMessage(e.getMessage());
            }
        }
    }
}
