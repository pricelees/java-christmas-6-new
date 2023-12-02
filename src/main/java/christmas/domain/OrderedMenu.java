package christmas.domain;

import christmas.constant.Menu;
import christmas.constant.Menu.Category;
import java.util.Map;

public class OrderedMenu {
    private final Map<Menu, Integer> menuAndCounts;

    public OrderedMenu(Map<Menu, Integer> menuAndCounts) {
        this.menuAndCounts = menuAndCounts;
    }

    public Map<Menu, Integer> getMenuAndCounts() {
        return menuAndCounts;
    }

    public int countMenuOf(Category category) {
        return menuAndCounts.keySet().stream()
                .filter(menu -> menu.getCategory() == category)
                .mapToInt(menuAndCounts::get)
                .sum();
    }
}
