package christmas.service.discount.strategy;

import christmas.constant.Date;
import christmas.domain.Customer;

public class ChristmasDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 1_000;
    private static final int DISCOUNT_AMOUNT_PER_DAY = 100;

    @Override
    public int calculateDiscountAmount(Customer customer) {
        if (customer.getDayOfMonth() <= Date.CHRISTMAS) {
            return DISCOUNT_AMOUNT_PER_DAY * (customer.getDayOfMonth() - Date.EVENT_START_DAY_OF_MONTH)
                    + DEFAULT_DISCOUNT_AMOUNT;
        }
        return ZERO_DISCOUNT_AMOUNT;
    }
}
