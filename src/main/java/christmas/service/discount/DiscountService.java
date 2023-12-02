package christmas.service.discount;

import christmas.domain.Customer;
import christmas.dto.response.BenefitsResponses.BenefitsResponse;
import java.util.Arrays;
import java.util.List;

public class DiscountService {
    public List<BenefitsResponse> receiveDiscountInfo(Customer customer) {
        return Arrays.stream(Discounts.values())
                .filter(discount -> discount.isApplicableDiscount(customer))
                .map(discount -> new BenefitsResponse(discount.getName(), discount.calculateDiscountAmount(customer)))
                .toList();
    }

    public int calculateTotalDiscountAmount(Customer customer) {
        return Arrays.stream(Discounts.values())
                .filter(discount -> discount.isApplicableDiscount(customer))
                .mapToInt(discount -> discount.calculateDiscountAmount(customer))
                .sum();
    }
}
