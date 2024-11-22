package christmas.domain.badge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Badge implements Comparator<Badge> {
	산타(20_000),
	트리(10_000),
	별(5_000),
	;
	
	private final int minMoney;
	
	Badge(int from) {
		this.minMoney = from;
	}
	
	//TODO : 혜택 금액에 따른 뱃지 종류 반환
	public static Optional<Badge> from(int eventDiscountCost) {
		return Arrays.stream(Badge.values())
				.filter(badge -> badge.minMoney <= eventDiscountCost)
				.sorted()
				.findFirst();
	}
	
	@Override
	public int compare(Badge o1, Badge o2) {
		return o2.minMoney - o1.minMoney;
	}
}
