package christmas.view.impl;

import christmas.dto.ReceiptDTO;
import christmas.view.OutputView;

public class ConsoleOutputView implements OutputView {

    public void printWelcomeMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printReceipt(ReceiptDTO receiptDTO) {
        System.out.println(receiptDTO.receipt());
    }
}
