package christmas.service.discount.strategy;

import christmas.domain.Customer;

public interface DiscountStrategy {
    int ZERO_DISCOUNT_AMOUNT = 0;
    int calculateDiscountAmount(Customer customer);
}
