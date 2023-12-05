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

class WeekdayDiscountStrategyTest {
    @DisplayName("정확한 힐은 금액 반환 확인")
    @ParameterizedTest(name = "12월 {0}일 방문, 디저트가 2개일 때 {1}원 할인 확인")
    @MethodSource("provideArguments")
    void calculateDiscountAmount(int dayOfMonth, int expectedDiscountAmount) {
        Customer customer = Customer.valueOf(
                dayOfMonth, Map.of(Menu.CHOCOLATE_CAKE, 2) // 메뉴는 디저트 2개로 지정
        );
        DiscountStrategy discountStrategy = new WeekdayDiscountStrategy();
        assertThat(discountStrategy.calculateDiscountAmount(customer))
                .isEqualTo(expectedDiscountAmount);

    }

    static Stream<Arguments> provideArguments() {
        return IntStream.rangeClosed(1, 31)
                .mapToObj(dayOfMonth -> {
                    if (Date.WEEKDAYS.contains(LocalDate.of(2023,12,dayOfMonth).getDayOfWeek())) {
                        return arguments(dayOfMonth, 2023 * 2);
                    }
                    return arguments(dayOfMonth, 0);
                });
    }
}