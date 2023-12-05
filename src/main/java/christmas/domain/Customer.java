package christmas.domain;

import christmas.constant.Date;
import christmas.constant.Menu;
import christmas.constant.Menu.Category;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class Customer {
    private final LocalDate date;
    private final OrderedMenu menu;

    private Customer(LocalDate date, OrderedMenu menu) {
        this.date = date;
        this.menu = menu;
    }

    public static Customer valueOf(int dayOfMonth, Map<Menu, Integer> menuAndCounts) {
        return new Customer(
                LocalDate.of(Date.YEAR, Date.MONTH, dayOfMonth),
                new OrderedMenu(menuAndCounts)
        );
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public OrderedMenu getMenu() {
        return menu;
    }

    public int calculateMenuCountOf(Category category) {
        return menu.countMenuOf(category);
    }
}
