package christmas.service.freemenu;

import christmas.dto.response.FreeMenuResponses.FreeMenuResponse;
import java.util.Arrays;
import java.util.List;

public class FreeMenuService {
    private static final int FREE_MENU_COUNT = 1;

    public List<FreeMenuResponse> receiveAllFreeMenu(int amountOfOrderedMenu) {
        return Arrays.stream(FreeMenu.values())
                .filter(freeMenu -> freeMenu.isApplicable(amountOfOrderedMenu))
                .map(freeMenu -> new FreeMenuResponse(freeMenu.getName(), FREE_MENU_COUNT, freeMenu.getPrice()))
                .toList();
    }

    public int calculateTotalFreeMenuPrice(int amountOfOrderedMenu) {
        return Arrays.stream(FreeMenu.values())
                .filter(freeMenu -> freeMenu.isApplicable(amountOfOrderedMenu))
                .mapToInt(FreeMenu::getPrice)
                .sum();
    }
}
