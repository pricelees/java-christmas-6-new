package christmas.service.freemenu;

import java.util.function.Predicate;

public enum FreeMenu {
    CHAMPAGNE("샴페인", amount -> amount >= 120_000, 25_000);

    private final String name;
    private final Predicate<Integer> condition;
    private final int price;

    FreeMenu(String name, Predicate<Integer> condition, int price) {
        this.name = name;
        this.condition = condition;
        this.price = price;
    }

    public boolean isApplicable(int amountOfOrderedMenu) {
        return condition.test(amountOfOrderedMenu);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
