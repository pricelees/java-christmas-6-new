package christmas.service.discount.strategy;

import christmas.domain.Customer;

public interface DiscountStrategy {
    int calculateDiscountAmount(Customer customer);
}
