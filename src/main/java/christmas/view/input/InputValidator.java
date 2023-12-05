package christmas.view.input;

import christmas.constant.ErrorMessages;
import java.util.HashSet;
import java.util.List;

public class InputValidator {
    private static final String ONLY_NUMBER_REGEX = "(\\d)+";
    private static final String MENU_AND_COUNT_REGEX = "(([가-힣]+)-(\\d+))(,([가-힣]+)-(\\d+))*";

    public void validateNumberFormat(String input) {
        if (hasNonNumber(input)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_DAY_OF_MONTH.getMessage());
        }
    }

    private boolean hasNonNumber(String input) {
        return !input.matches(ONLY_NUMBER_REGEX);
    }

    public void validateMenuAndCount(String menuAndCount) {
        validateMenuAndCountFormat(menuAndCount);
        validateDuplicateMenu(menuAndCount);
    }

    public void validateMenuAndCountFormat(String input) {
        if (isInvalidFormat(input)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MENU_AND_COUNT.getMessage());
        }
    }

    private boolean isInvalidFormat(String input) {
        return !input.matches(MENU_AND_COUNT_REGEX);
    }

    private void validateDuplicateMenu(String menuAndCount) {
        if (hasDuplicateMenu(menuAndCount)) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_MENU_AND_COUNT.getMessage());
        }
    }

    private boolean hasDuplicateMenu(String menuAndCounts) {
        List<String> menuNames = MenuNameCountMapper.mapToMenuName(menuAndCounts);

        return new HashSet<>(menuNames).size() != menuNames.size();
    }
}
