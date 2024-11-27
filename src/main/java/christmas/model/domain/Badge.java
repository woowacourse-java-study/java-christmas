package christmas.model.domain;
import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    private final String name;
    private final int minBenefit;

    Badge(String name, int minBenefit) {
        this.name = name;
        this.minBenefit = minBenefit;
    }

    public String getName() {
        return name;
    }

    public static Badge getBadgeByBenefit(int totalBenefit) {
        return Arrays.stream(values())
                .filter(badge -> totalBenefit >= badge.minBenefit)
                .findFirst()
                .orElse(NONE);
    }
}
