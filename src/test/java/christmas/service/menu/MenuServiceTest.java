package christmas.service.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.constant.Menu;
import christmas.domain.Customer;
import christmas.dto.response.OrderedMenuResponses.OrderedMenuResponse;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuServiceTest {
    MenuService menuService = new MenuService();
    Customer customer;

    @BeforeEach
    void setUp() {
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        orderedMenu.put(Menu.TAPAS, 1);
        orderedMenu.put(Menu.T_BONE_STEAK, 1);
        orderedMenu.put(Menu.ICE_CREAM, 1);
        orderedMenu.put(Menu.RED_WINE, 1);

        // 날짜는 임의 지정, 메뉴는 카테고리당 1개씩 지정
        customer = Customer.valueOf(25, orderedMenu);
    }

    @DisplayName("주문한 메뉴 목록을 정확히 반환하는지 확인")
    @Test
    void receiveOrderedMenuInfo() {
        List<OrderedMenuResponse> expected = List.of(
                new OrderedMenuResponse("타파스", 1),
                new OrderedMenuResponse("티본스테이크", 1),
                new OrderedMenuResponse("아이스크림", 1),
                new OrderedMenuResponse("레드와인", 1)
        );
        assertThat(menuService.receiveOrderedMenuInfo(customer)).isEqualTo(expected);
    }

    @DisplayName("주문한 메뉴의 총 가격을 정확히 계산하는지 확인")
    @Test
    void calculateOrderedMenuAmount() {
        int expected = 125_500;
        assertThat(menuService.calculateOrderedMenuAmount(customer)).isEqualTo(expected);
    }
}