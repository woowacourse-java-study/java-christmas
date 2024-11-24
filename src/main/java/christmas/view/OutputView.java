package christmas.view;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Product;
import christmas.model.Receipt;
import java.text.NumberFormat;

public class OutputView {
    private static final NumberFormat numberFormat = NumberFormat.getInstance();

    public void responseWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void responseDateIntro() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void responseMenuIntro() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void responseErrorMessage(String message) {
        System.out.println(message);
    }

    public void responseReceiptInto(Date date) {
        System.out.println("12월 " + date.getDay() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void responseReceipt(Cart cart, Receipt receipt) {
        printOrderedMenu(cart);
        printTotal(cart);
        printGiftMenu(receipt);
        printEvent(receipt);
        printTotalDiscount(receipt);
        printFinal(cart, receipt);
        printBadge(receipt);
    }

    private static void printFinal(Cart cart, Receipt receipt) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(numberFormat.format(cart.getTotalPrice() - receipt.getTotalDiscount()) + "원");
    }

    private static void printBadge(Receipt receipt) {
        System.out.println("\n<12월 이벤트 배지>");
        if (!receipt.getBadge().isBlank()) {
            System.out.println(receipt.getBadge());
            return;
        }
        System.out.println("없음");
    }

    private static void printTotalDiscount(Receipt receipt) {
        System.out.println("\n<총혜택 금액>");
        if (receipt.getTotalDiscount() != 0) {
            System.out.println("-" + numberFormat.format(receipt.getTotalDiscount()) + "원");
            return;
        }
        System.out.println("없음");
    }


    private void printOrderedMenu(Cart cart) {
        System.out.println("\n<주문 메뉴>");
        for (Product product : cart.getProducts()) {
            System.out.println(product.getName() + " " + product.getQuantity() + "개");
        }
    }

    private void printTotal(Cart cart) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(numberFormat.format(cart.getTotalPrice()) + "원");
    }

    private void printGiftMenu(Receipt receipt) {
        System.out.println("\n<증정 메뉴>");
        if (receipt.getGiftEvent() != null) {
            for (Product product : receipt.getGiftEvent()) {
                System.out.println(product.getName() + " " + product.getQuantity() + "개");
            }
            return;
        }
        System.out.println("없음");
    }

    private void printEvent(Receipt receipt) {
        System.out.println("\n<혜택 내역>");
        if (receipt.getTotalDiscount() == 0) {
            System.out.println("없음");
            return;
        }
        printChristmasDiscount(receipt);
        printWeekdayDiscount(receipt);
        printWeekendDiscount(receipt);
        printSpecialDiscount(receipt);
        printGiftDiscount(receipt);
    }

    private void printGiftDiscount(Receipt receipt) {
        if (receipt.getSpecialEventDiscount() != 0) {
            System.out.println("증정 이벤트: -" + numberFormat.format(receipt.getGiftEventDiscount()) + "원");
        }
    }

    private void printSpecialDiscount(Receipt receipt) {
        if (receipt.getSpecialEventDiscount() != 0) {
            System.out.println("특별 이벤트: -" + numberFormat.format(receipt.getSpecialEventDiscount()) + "원");
        }
    }

    private void printWeekendDiscount(Receipt receipt) {
        if (receipt.getWeekendEventDiscount() != 0) {
            System.out.println("주말 할인: -" + numberFormat.format(receipt.getWeekendEventDiscount()) + "원");
        }
    }

    private void printWeekdayDiscount(Receipt receipt) {
        if (receipt.getWeekdayEventDiscount() != 0) {
            System.out.println("평일 할인: -" + numberFormat.format(receipt.getWeekdayEventDiscount()) + "원");
        }
    }

    private void printChristmasDiscount(Receipt receipt) {
        if (receipt.getChristmasEventDiscount() != 0) {
            System.out.println("크리스마스 디데이 할인: -" + numberFormat.format(receipt.getChristmasEventDiscount()) + "원");
        }
    }
}
