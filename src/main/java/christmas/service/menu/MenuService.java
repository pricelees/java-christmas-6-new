package christmas.service.menu;

import christmas.constant.Menu;
import christmas.domain.Customer;
import christmas.domain.OrderedMenu;
import christmas.dto.response.OrderedMenuResponses.OrderedMenuResponse;
import java.util.List;
import java.util.Map;

public class MenuService {
    public List<OrderedMenuResponse> receiveOrderedMenuInfo(Customer customer) {
        OrderedMenu orderedMenu = customer.getMenu();
        Map<Menu, Integer> menuAndCount = orderedMenu.getMenuAndCounts();

        return menuAndCount.keySet().stream()
                .map(menu -> new OrderedMenuResponse(menu.getName(), menuAndCount.get(menu)))
                .toList();
    }

    public int calculateOrderedMenuAmount(Customer customer) {
        OrderedMenu orderedMenu = customer.getMenu();
        Map<Menu, Integer> menuAndCount = orderedMenu.getMenuAndCounts();

        return menuAndCount.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * menuAndCount.get(menu))
                .sum();
    }
}
