package christmas.view.input;

import christmas.constant.ErrorMessages;
import christmas.constant.Menu;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MenuNameCountMapper {
    private static final String MENU_NAME = "name";
    private static final String MENU_COUNT = "count";
    private static final String MENU_DELIMITER = ",";
    private static final String NAME_COUNT_DELIMITER = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_COUNT_INDEX = 1;

    private MenuNameCountMapper() {
    }

    public static Map<Menu, Integer> map(String menuAndCounts) {
        EnumMap<Menu, Integer> result = new EnumMap<>(Menu.class);
        Arrays.stream(menuAndCounts.split(MENU_DELIMITER))
                .forEach(menuAndCount -> {
                    Menu menu = Menu.from(splitMenuAndCountTo(MENU_NAME, menuAndCount))
                            .orElseThrow(() -> new IllegalArgumentException(
                                    ErrorMessages.INVALID_MENU_AND_COUNT.getMessage()));
                    int count = Integer.parseInt(splitMenuAndCountTo(MENU_COUNT, menuAndCount));
                    result.put(menu, count);
                });

        return result;
    }

    private static String splitMenuAndCountTo(String order, String menuAndCount) {
        if (order.equals(MENU_NAME)) {
            return menuAndCount.split(NAME_COUNT_DELIMITER)[MENU_NAME_INDEX];
        }
        return menuAndCount.split(NAME_COUNT_DELIMITER)[MENU_COUNT_INDEX];
    }

    public static List<String> mapToMenuName(String menuAndCounts) {
        return Arrays.stream(menuAndCounts.split(MENU_DELIMITER))
                .map(menuAndCount -> menuAndCount.split(NAME_COUNT_DELIMITER)[MENU_NAME_INDEX])
                .toList();
    }
}
