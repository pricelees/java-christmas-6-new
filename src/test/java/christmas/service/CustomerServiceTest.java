package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.Menu;
import christmas.domain.Customer;
import christmas.dto.response.AmountResponse;
import christmas.dto.response.BenefitsResponses;
import christmas.dto.response.BenefitsResponses.BenefitsResponse;
import christmas.service.badge.BadgeService;
import christmas.service.discount.DiscountService;
import christmas.service.freemenu.FreeMenuService;
import christmas.service.menu.MenuService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerServiceTest {
    CustomerService customerService = new CustomerService(
            new MenuService(),
            new DiscountService(),
            new FreeMenuService(),
            new BadgeService()
    );
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = Customer.valueOf(25, Map.of(
                Menu.MUSHROOM_SOUP, 1,
                Menu.BARBECUE_RIBS, 2,
                Menu.CHOCOLATE_CAKE, 1,
                Menu.ZERO_COLA, 1
        ));
    }

    @DisplayName("금액과 관련된 정보를 정확하게 반환하는지 확인")
    @Test
    void receiveAllAmountInfo() {
        AmountResponse expected = new AmountResponse(132_000, 125_577, 31_423);
        assertThat(customerService.receiveAllAmountInfo(customer)).isEqualTo(expected);
    }

    @DisplayName("총 혜택 내역을 정확하게 반환하는지 확인")
    @Test
    void receiveAllBenefitsInfo() {
        BenefitsResponses expected = new BenefitsResponses(List.of(
                new BenefitsResponse("크리스마스 디데이 할인", 3_400),
                new BenefitsResponse("평일 할인", 2_023),
                new BenefitsResponse("특별 할인", 1_000),
                new BenefitsResponse("증정 이벤트", 25_000)
        ));

        assertThat(customerService.receiveAllBenefitsInfo(customer)).isEqualTo(expected);
    }
}