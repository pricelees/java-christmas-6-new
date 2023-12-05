package christmas.service.discount;

import christmas.constant.Date;
import christmas.domain.Customer;
import christmas.service.discount.strategy.ChristmasDiscountStrategy;
import christmas.service.discount.strategy.DiscountStrategy;
import christmas.service.discount.strategy.SpecialDiscountStrategy;
import christmas.service.discount.strategy.WeekdayDiscountStrategy;
import christmas.service.discount.strategy.WeekendDiscountStrategy;
import java.util.function.Predicate;

public enum Discounts {
    CHRISTMAS_DISCOUNT(
            "크리스마스 디데이 할인",
            customer -> customer.getDayOfMonth() <= Date.CHRISTMAS,
            new ChristmasDiscountStrategy()
    ),
    WEEKDAY_DISCOUNT(
            "평일 할인",
            customer -> Date.WEEKDAYS.contains(customer.getDayOfWeek()),
            new WeekdayDiscountStrategy()
    ),
    WEEKEND_DISCOUNT(
            "주말 할인",
            customer -> Date.WEEKENDS.contains(customer.getDayOfWeek()),
            new WeekendDiscountStrategy()
    ),
    SPECIAL_DISCOUNT(
            "특별 할인",
            customer -> Date.SPECIAL_DAYS.contains(customer.getDayOfMonth()),
            new SpecialDiscountStrategy()
    );

    private final String name;
    private final Predicate<Customer> condition;
    private final DiscountStrategy strategy;

    Discounts(String name, Predicate<Customer> condition, DiscountStrategy strategy) {
        this.name = name;
        this.condition = condition;
        this.strategy = strategy;
    }

    public boolean isApplicableDiscount(Customer customer) {
        return condition.test(customer);
    }

    public int calculateDiscountAmount(Customer customer) {
        return strategy.calculateDiscountAmount(customer);
    }

    public String getName() {
        return name;
    }
}
