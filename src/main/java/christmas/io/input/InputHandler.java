package christmas.io.input;

import christmas.common.dto.OrderCreateDto;
import christmas.io.reader.Reader;
import christmas.io.writer.Writer;

import java.time.LocalDate;
import java.util.List;

public class InputHandler {
	
	private final Writer writer;
	private final Reader reader;
	private final InputParser inputParser;
	private final InputValidator inputValidator;
	
	public InputHandler(Writer writer, Reader reader, InputParser inputParser, InputValidator inputValidator) {
		this.writer = writer;
		this.reader = reader;
		this.inputParser = inputParser;
		this.inputValidator = inputValidator;
	}
	
	public LocalDate getOrderDate(int year, int month) {
		writer.write("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n".formatted(month));
		writer.write("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n".formatted(month));
		String orderDay = reader.readLine();
		inputValidator.validateOrderDay(orderDay);
		return inputParser.parseOrderDay(year, month, orderDay);
	}
	
	public List<OrderCreateDto> getOrders() {
		writer.write("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n");
		String orders = reader.readLine();
		inputValidator.validateOrders(orders);
		return inputParser.parseOrders(orders);
	}
}
