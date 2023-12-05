package christmas.service.discount.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.constant.Date;
import christmas.constant.Menu;
import christmas.domain.Customer;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SpecialDiscountStrategyTest {
    @DisplayName("정확한 힐은 금액 반환 확인")
    @ParameterizedTest(name = "12월 {0}일 방문시 {1}원 할인 확인")
    @MethodSource("provideArguments")
    void calculateDiscountAmount(int dayOfMonth, int expectedDiscountAmount) {
        Customer customer = Customer.valueOf(
                dayOfMonth, Map.of(Menu.CHRISTMAS_PASTA, 1) // 메뉴는 임의 지정
        );
        DiscountStrategy discountStrategy = new SpecialDiscountStrategy();
        assertThat(discountStrategy.calculateDiscountAmount(customer))
                .isEqualTo(expectedDiscountAmount);

    }

    static Stream<Arguments> provideArguments() {
        return IntStream.rangeClosed(1, 31)
                .mapToObj(dayOfMonth -> {
                    if (Date.SPECIAL_DAYS.contains(dayOfMonth)) {
                        return arguments(dayOfMonth, 1000);
                    }
                    return arguments(dayOfMonth, 0);
                });
    }
}