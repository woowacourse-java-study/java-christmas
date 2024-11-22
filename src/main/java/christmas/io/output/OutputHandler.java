package christmas.io.output;

import christmas.common.dto.EventResultDto;
import christmas.common.dto.OrderMenuDto;
import christmas.common.dto.PromotionDto;
import christmas.domain.badge.Badge;
import christmas.io.writer.Writer;

import java.util.List;
import java.util.Optional;

public class OutputHandler {
	
	private static final String MENU_FORMAT = "%s %,d개\n";
	
	private final Writer writer;
	
	public OutputHandler(Writer writer) {
		this.writer = writer;
	}
	
	public void handleOrderMenus(List<OrderMenuDto> orderMenuDtos, int month, int day) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n".formatted(month, day));
		stringBuilder.append("\n<주문 메뉴>\n");
		orderMenuDtos.stream()
				.forEach(orderMenuDto -> stringBuilder.append(MENU_FORMAT.formatted(orderMenuDto.menuName(), orderMenuDto.amount())));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalCostBeforeEvent(int totalCost) {
		writer.write("\n<할인 전 총주문 금액>\n%,d원\n".formatted(totalCost));
	}
	
	public void handlePromotionMenus(List<PromotionDto> promotionDtos) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n<증정 메뉴>\n");
		promotionDtos.stream()
				.forEach(promotionDto -> stringBuilder.append(MENU_FORMAT.formatted(promotionDto.menu().name(), promotionDto.amount())));
		writer.write(stringBuilder.toString());
	}
	
	public void handleEvents(List<EventResultDto> events) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n<혜택 내역>\n");
		events.stream().filter(event -> event.discountAmount() != 0)
				.forEach(eventResultDto -> stringBuilder.append("%s: -%,d원\n".formatted(eventResultDto.name(), eventResultDto.discountAmount())));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalDiscountCost(int eventTotalDiscount) {
		writer.write("\n<총혜택 금액>\n-%,d원\n".formatted(eventTotalDiscount));
	}
	
	public void handleTotalCostAfterEvent(int totalCostAfterDiscount) {
		writer.write("\n<할인 후 예상 결제 금액>\n%,d원\n".formatted(totalCostAfterDiscount));
	}
	
	public void handleBadge(Optional<Badge> badge) {
		//TODO : 12월 고쳐야함
		writer.write("\n<12월 이벤트 배지>\n%s".formatted(getBadgeName(badge)));
	}
	
	private String getBadgeName(Optional<Badge> badge) {
		return badge.map(Enum::name).orElse("없음");
	}
}
