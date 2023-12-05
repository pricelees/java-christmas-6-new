package christmas.service.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Menu;
import christmas.domain.Customer;
import christmas.dto.response.BenefitsResponses.BenefitsResponse;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {
    DiscountService discountService = new DiscountService();
    Customer customer;

    @BeforeEach
    void setUp() {
        // 방문일은 25일, 메뉴는 각 카테고리에서 1개씩 선택
        customer = Customer.valueOf(25,
                Map.of(Menu.MUSHROOM_SOUP, 1, Menu.T_BONE_STEAK, 1, Menu.ICE_CREAM, 1, Menu.RED_WINE, 1));
    }

    @DisplayName("할인 이름과 금액을 정확히 반환하는지 확인")
    @Test
    void receiveDiscountInfo() {
        List<BenefitsResponse> expected = List.of(
                new BenefitsResponse("크리스마스 디데이 할인", 3_400),
                new BenefitsResponse("평일 할인", 2_023),
                new BenefitsResponse("특별 할인", 1_000)
        );
        List<BenefitsResponse> actual = discountService.receiveDiscountInfo(customer);

        assertThat(actual).isEqualTo(expected);
    }


    @DisplayName("총 할인 금액을 정확히 계산하는지 확인")
    @Test
    void calculateTotalDiscountAmount() {
        int expected = 3_400 + 2_023 + 1_000;
        int actual = discountService.calculateTotalDiscountAmount(customer);

        assertThat(actual).isEqualTo(expected);
    }
}