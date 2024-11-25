package christmas.common.dto;

import christmas.domain.menu.Menu;

public record PromotionDto(
		Menu menu,
		int amount
) {
}
