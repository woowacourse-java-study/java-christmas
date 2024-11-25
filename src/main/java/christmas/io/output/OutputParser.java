package christmas.io.output;

import christmas.common.dto.EventResultDto;
import christmas.common.dto.OrderMenuDto;
import christmas.common.dto.PromotionDto;
import christmas.domain.badge.Badge;

import java.util.List;
import java.util.Optional;

public class OutputParser {
	
	private static final String MENU_FORMAT = "%s %,d개\n";
	
	public String parseMenus(List<OrderMenuDto> orderMenuDtos) {
		if (orderMenuDtos.isEmpty()) return "없음\n";
		StringBuilder stringBuilder = new StringBuilder();
		orderMenuDtos.forEach(orderMenuDto -> stringBuilder.append(MENU_FORMAT.formatted(orderMenuDto.menuName(), orderMenuDto.amount())));
		return stringBuilder.toString();
	}
	
	public String parsePromotions(List<PromotionDto> promotionDtos) {
		if (promotionDtos.isEmpty()) return "없음\n";
		StringBuilder stringBuilder = new StringBuilder();
		promotionDtos.forEach(promotionDto -> stringBuilder.append(MENU_FORMAT.formatted(promotionDto.menu().name(), promotionDto.amount())));
		return stringBuilder.toString();
	}
	
	public String parseEvents(List<EventResultDto> events) {
		List<EventResultDto> validEvents = events.stream()
				.filter(event -> event.discountAmount() != 0)
				.toList();
		if (validEvents.isEmpty()) return "없음\n";
		StringBuilder stringBuilder = new StringBuilder();
		validEvents.forEach(eventResultDto -> stringBuilder.append("%s: -%,d원\n".formatted(eventResultDto.name(), eventResultDto.discountAmount())));
		return stringBuilder.toString();
	}
	
	
	public Object parseBadgeName(Optional<Badge> badge) {
		return badge.map(Enum::name).orElse("없음");
	}
	
	public String parseTotalDiscountCost(int eventTotalDiscount) {
		if (eventTotalDiscount == 0) return "0원\n";
		return "-%,d원\n".formatted(eventTotalDiscount);
	}
}
