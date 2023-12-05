package christmas.service.discount.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.constant.Date;
import christmas.constant.Menu;
import christmas.domain.Customer;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeekendDiscountStrategyTest {
    @DisplayName("정확한 힐은 금액 반환 확인")
    @ParameterizedTest(name = "12월 {0}일 방문, 메인 메뉴가 3개일 때 {1}원 할인 확인")
    @MethodSource("provideArguments")
    void calculateDiscountAmount(int dayOfMonth, int expectedDiscountAmount) {
        Customer customer = Customer.valueOf(
                dayOfMonth, Map.of(Menu.T_BONE_STEAK, 1, Menu.BARBECUE_RIBS, 2) // 메뉴는 메인메뉴 3개로 지정
        );
        DiscountStrategy discountStrategy = new WeekendDiscountStrategy();
        assertThat(discountStrategy.calculateDiscountAmount(customer))
                .isEqualTo(expectedDiscountAmount);

    }

    static Stream<Arguments> provideArguments() {
        return IntStream.rangeClosed(1, 31)
                .mapToObj(dayOfMonth -> {
                    if (Date.WEEKENDS.contains(LocalDate.of(2023,12,dayOfMonth).getDayOfWeek())) {
                        return arguments(dayOfMonth, 2023 * 3);
                    }
                    return arguments(dayOfMonth, 0);
                });
    }

}