package christmas.service.badge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeServiceTest {

    @DisplayName("총 혜택 금액에 따른 배지 이름 반환 확인")
    @ParameterizedTest(name = "총 혜택 금액이 {0}원일 때 {1} 증정 확인")
    @MethodSource("provideArguments")
    void receiveBadgeInfo(int amount, String badgeName) {
        BadgeService badgeService = new BadgeService();
        assertThat(badgeService.receiveBadgeInfo(amount))
                .isEqualTo(badgeName);
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                arguments(4_999, "없음"),
                arguments(5_000, "별"),
                arguments(9_999, "별"),
                arguments(10_000, "트리"),
                arguments(19_999, "트리"),
                arguments(20_000, "산타")
        );
    }
}