package christmas.service.badge;

import java.util.Arrays;

public class BadgeService {
    public String receiveBadgeInfo(int benefitsAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.isApplicable(benefitsAmount))
                .findFirst()
                .orElse(Badge.NONE).getName();
    }
}
