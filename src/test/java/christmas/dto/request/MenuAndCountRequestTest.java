package christmas.dto.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.Menu;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuAndCountRequestTest {
    @DisplayName("메뉴의 개수가 20개 이상일때의 예외 발생 확인")
    @Test
    void menuAndCount() {
        Map<Menu, Integer> menuAndCount = Map.of(
                Menu.ICE_CREAM, 21
        );
        assertThatThrownBy(() -> new MenuAndCountRequest(menuAndCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문");
    }
}