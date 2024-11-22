package christmas.io.output;

import christmas.common.dto.EventResultDto;
import christmas.common.dto.OrderMenuDto;
import christmas.common.dto.PromotionDto;
import christmas.domain.badge.Badge;
import christmas.io.writer.Writer;

import java.util.List;
import java.util.Optional;

public class OutputHandler {
	private final Writer writer;
	private final OutputParser outputParser;
	
	public OutputHandler(Writer writer, OutputParser outputParser) {
		this.writer = writer;
		this.outputParser = outputParser;
	}
	
	public void handleOrderMenus(List<OrderMenuDto> orderMenuDtos, int month, int day) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n".formatted(month, day));
		stringBuilder.append("\n<주문 메뉴>\n");
		stringBuilder.append(outputParser.parseMenus(orderMenuDtos));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalCostBeforeEvent(int totalCost) {
		writer.write("\n<할인 전 총주문 금액>\n%,d원\n".formatted(totalCost));
	}
	
	public void handlePromotionMenus(List<PromotionDto> promotionDtos) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n<증정 메뉴>\n");
		stringBuilder.append(outputParser.parsePromotions(promotionDtos));
		writer.write(stringBuilder.toString());
	}
	
	public void handleEvents(List<EventResultDto> events) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n<혜택 내역>\n");
		stringBuilder.append(outputParser.parseEvents(events));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalDiscountCost(int eventTotalDiscount) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n<총혜택 금액>\n");
		stringBuilder.append(outputParser.parseTotalDiscountCost(eventTotalDiscount));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalCostAfterEvent(int totalCostAfterDiscount) {
		writer.write("\n<할인 후 예상 결제 금액>\n%,d원\n".formatted(totalCostAfterDiscount));
	}
	
	public void handleBadge(Optional<Badge> badge, int month) {
		writer.write("\n<%d월 이벤트 배지>\n%s".formatted(month, outputParser.parseBadgeName(badge)));
	}
	
	
}
