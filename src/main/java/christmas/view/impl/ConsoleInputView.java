package christmas.view.impl;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.InputView;

public class ConsoleInputView implements InputView {

    public String readDate(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Console.readLine();
    }

    public String readOrders(){
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해물파스타-2,레드와인-1,초코케이크-1");
        return Console.readLine();
    }
}
