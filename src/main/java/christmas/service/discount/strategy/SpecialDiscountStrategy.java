package christmas.service.discount.strategy;

import christmas.domain.Customer;

public class SpecialDiscountStrategy implements DiscountStrategy {
    private static final int DISCOUNT_AMOUNT = 1_000;

    @Override
    public int calculateDiscountAmount(Customer customer) {
        return DISCOUNT_AMOUNT;
    }
}
