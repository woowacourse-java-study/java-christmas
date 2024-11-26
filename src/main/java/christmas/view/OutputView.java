package christmas.view;

import christmas.dto.ReceiptDTO;

public interface OutputView {
    void printWelcomeMessage();
    void printReceipt(ReceiptDTO receiptDTO);
}
