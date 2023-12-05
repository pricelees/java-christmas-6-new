package christmas.service.discount.strategy;

import christmas.constant.Date;
import christmas.constant.Menu.Category;
import christmas.domain.Customer;

public class WeekdayDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 2_023;

    @Override
    public int calculateDiscountAmount(Customer customer) {
        if (Date.WEEKDAYS.contains(customer.getDayOfWeek())) {
            return customer.calculateMenuCountOf(Category.DESSERTS) * DEFAULT_DISCOUNT_AMOUNT;
        }
        return ZERO_DISCOUNT_AMOUNT;
    }
}
