package christmas.service.badge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeTest {

    @DisplayName("조건에 따른 배지 증정 여부 확인")
    @ParameterizedTest(name = "{0}원에 대한 {1} 증정 여부 확인")
    @MethodSource("provideArguments")
    void isApplicable(int amount, Badge badge, boolean expectedResult) {
        assertThat(badge.isApplicable(amount))
                .isEqualTo(expectedResult);
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                arguments(4_999, Badge.STAR_BADGE, false),
                arguments(5_000, Badge.STAR_BADGE, true),
                arguments(9_999, Badge.TREE_BADGE, false),
                arguments(10_000, Badge.TREE_BADGE, true),
                arguments(19_999, Badge.SANTA_BADGE, false),
                arguments(20_000, Badge.SANTA_BADGE, true)
        );
    }
}