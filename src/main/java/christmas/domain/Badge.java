package christmas.domain;

import java.math.BigDecimal;

public enum Badge {
    NONE("없음", BigDecimal.valueOf(0)),
    STAR("별", BigDecimal.valueOf(5000)),
    TREE("트리", BigDecimal.valueOf(10000)),
    SANTA("산타", BigDecimal.valueOf(20000));

    private final String displayName;
    private final BigDecimal minimumDiscount;

    Badge(String displayName, BigDecimal minimumDiscount) {
        this.displayName = displayName;
        this.minimumDiscount = minimumDiscount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getMinimumDiscount() {
        return minimumDiscount;
    }

    public static Badge getBadgeForDiscount(BigDecimal discount) {
        Badge assignedBadge = NONE;
        for (Badge badge : Badge.values()) {
            if (discount.compareTo(badge.getMinimumDiscount()) >= 0) {
                assignedBadge = badge;
            }
        }
        return assignedBadge;
    }
}
