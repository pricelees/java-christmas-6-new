package christmas.dto.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayRequestTest {
    @DisplayName("유효하지 않은 일 정보에 대한 예외 발생 확인")
    @ParameterizedTest(name = "12월 {0}일인 경우")
    @ValueSource(ints = {-1, 0, 32})
    void constructor_WithInvalidInput_ThrowsException(int dayOfMonth) {
        assertThatThrownBy(() -> new DayRequest(dayOfMonth))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("날짜");
    }
}