package christmas.constant;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    // Appetite
    MUSHROOM_SOUP(Category.APPETITE, "양송이수프", 6_000),
    TAPAS(Category.APPETITE, "타파스", 5_500),
    CAESAR_SALAD(Category.APPETITE, "시저샐러드", 8_000),

    // Main Courses
    T_BONE_STEAK(Category.MAIN_COURSE, "티본스테이크", 55_000),
    BARBECUE_RIBS(Category.MAIN_COURSE, "바비큐립", 54_000),
    SEAFOOD_PASTA(Category.MAIN_COURSE, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(Category.MAIN_COURSE, "크리스마스파스타", 25_000),

    // Desserts
    CHOCOLATE_CAKE(Category.DESSERTS, "초코케이크", 15_000),
    ICE_CREAM(Category.DESSERTS, "아이스크림", 5_000),

    // Beverages
    ZERO_COLA(Category.BEVERAGES, "제로콜라", 3_000),
    RED_WINE(Category.BEVERAGES, "레드와인", 60_000),
    CHAMPAGNE(Category.BEVERAGES, "샴페인", 25_000);

    private final Category category;
    private final String name;
    private final int price;

    Menu(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Optional<Menu> from(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> name.equals(menu.name))
                .findFirst();
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public enum Category {
        APPETITE,
        MAIN_COURSE,
        DESSERTS,
        BEVERAGES;
    }
}
