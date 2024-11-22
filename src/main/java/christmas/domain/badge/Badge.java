package christmas.domain.badge;

import java.util.Arrays;
import java.util.Optional;

public enum Badge {
	별(5_000),
	트리(10_000),
	산타(20_000),
	;
	
	private final int from;
	
	Badge(int from) {
		this.from = from;
	}
	
	//TODO : 혜택 금액에 따른 뱃지 종류 반환
	public static Optional<Badge> from(int eventDiscountCost) {
		return Arrays.stream(Badge.values())
				.filter(badge -> badge.from >= eventDiscountCost)
				.findFirst();
	}
}
