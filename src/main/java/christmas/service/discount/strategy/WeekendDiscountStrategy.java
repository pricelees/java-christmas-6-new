package christmas.service.discount.strategy;

import christmas.constant.Menu.Category;
import christmas.domain.Customer;

public class WeekendDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 2_023;

    @Override
    public int calculateDiscountAmount(Customer customer) {
        return customer.calculateMenuCountOf(Category.MAIN_COURSE) * DEFAULT_DISCOUNT_AMOUNT;
    }
}
