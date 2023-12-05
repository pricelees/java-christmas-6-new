package christmas.service.freemenu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import christmas.constant.Menu;
import christmas.domain.Customer;
import christmas.dto.response.FreeMenuResponses.FreeMenuResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class FreeMenuServiceTest {
    FreeMenuService freeMenuService = new FreeMenuService();

    @DisplayName("증정 메뉴를 정확히 반환하는지 확인")
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideCustomerInfo")
    void receiveAllFreeMenu(String testName, int amountOfOrderedMenu, List<FreeMenuResponse> expected) {
        assertThat(freeMenuService.receiveAllFreeMenu(amountOfOrderedMenu))
                .isEqualTo(expected);
    }

    @DisplayName("증정 메뉴의 가격 합계를 정확히 계산하는지 확인")
    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "12만원 이상 구매 / 120_000 / 25_000",
            "12만원 미만 구매 / 119_999 / 0"
    }, delimiter = '/')
    void calculateTotalFreeMenuPrice(String testName, int amountOfOrderedMenu, int expected) {
        assertThat(freeMenuService.calculateTotalFreeMenuPrice(amountOfOrderedMenu))
                .isEqualTo(expected);
    }

    static Stream<Arguments> provideCustomerInfo() {
        return Stream.of(
                arguments(
                        "12만원 이상 구매",
                        120_000,
                        List.of(new FreeMenuResponse("샴페인", 1, 25_000))
                ),
                arguments(
                        "12만원 미만 구매",
                        119_000,
                        List.of()
                )
        );
    }
}