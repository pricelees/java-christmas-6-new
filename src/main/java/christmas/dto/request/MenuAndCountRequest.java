package christmas.dto.request;

import christmas.constant.ErrorMessages;
import christmas.constant.Menu;
import java.util.Collection;
import java.util.Map;

public record MenuAndCountRequest(Map<Menu, Integer> menuAndCount) {
    private static final int MAX_MENU_COUNTS = 20;

    public MenuAndCountRequest {
        validateMenuCount(menuAndCount.values());
    }

    private void validateMenuCount(Collection<Integer> counts) {
        if (hasMenuCountOverMaxCount(counts)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MENU_AND_COUNT.getMessage());
        }
    }

    private boolean hasMenuCountOverMaxCount(Collection<Integer> counts) {
        return counts.stream().mapToInt(Integer::intValue).sum() > MAX_MENU_COUNTS;
    }
}
