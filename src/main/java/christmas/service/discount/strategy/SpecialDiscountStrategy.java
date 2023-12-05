package christmas.service.discount.strategy;

import christmas.constant.Date;
import christmas.domain.Customer;

public class SpecialDiscountStrategy implements DiscountStrategy {
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public int calculateDiscountAmount(Customer customer) {
        if (Date.SPECIAL_DAYS.contains(customer.getDayOfMonth())) {
            return DISCOUNT_AMOUNT;
        }
        return ZERO_DISCOUNT_AMOUNT;
    }
}
