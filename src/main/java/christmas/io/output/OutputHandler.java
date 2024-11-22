package christmas.io.output;

import christmas.domain.badge.Badge;
import christmas.dto.EventResultDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionDto;
import christmas.io.writer.Writer;

import java.util.List;
import java.util.Optional;

public class OutputHandler {
	
	private static final String MENU_FORMAT = "%s %,d개\n";
	
	private final Writer writer;
	
	public OutputHandler(Writer writer) {
		this.writer = writer;
	}
	
	public void handleOrderMenus(List<OrderMenuDto> orderMenuDtos) {
		StringBuilder stringBuilder = new StringBuilder();
		orderMenuDtos.forEach(orderMenuDto ->
				stringBuilder.append(MENU_FORMAT.formatted(orderMenuDto.menuName(), orderMenuDto.amount())));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalCostBeforeEvent(int totalCost) {
		writer.write("<할인 전 총주문 금액>\n%,d원".formatted(totalCost));
	}
	
	public void handlePromotionMenus(List<PromotionDto> promotionDtos) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<증정 메뉴>\n");
		promotionDtos.forEach(promotionDto ->
				stringBuilder.append(MENU_FORMAT.formatted(promotionDto.menu().name(), promotionDto.amount())));
		writer.write(stringBuilder.toString());
	}
	
	public void handleEvents(List<EventResultDto> events) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<혜택 내역>\n");
		events.forEach(eventResultDto ->
				stringBuilder.append("%s: -%,d원".formatted(eventResultDto.name(), eventResultDto.discountAmount())));
		writer.write(stringBuilder.toString());
	}
	
	public void handleTotalDiscountCost(int eventTotalDiscount) {
		writer.write("<총혜택 금액>\n-%,d원".formatted(eventTotalDiscount));
	}
	
	public void handleTotalCostAfterEvent(int totalCostAfterDiscount) {
		writer.write("<할인 후 예상 결제 금액>\n%,d원".formatted(totalCostAfterDiscount));
	}
	
	public void handleBadge(Optional<Badge> badge) {
		//TODO : 12월 고쳐야함
		writer.write("<12월 이벤트 배지>\n%s".formatted(getBadgeName(badge)));
	}
	
	private String getBadgeName(Optional<Badge> badge) {
		return badge.map(Enum::name).orElse("없음");
	}
}
