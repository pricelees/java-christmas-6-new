package christmas.view.input;

import christmas.dto.request.DayRequest;
import christmas.dto.request.MenuAndCountRequest;
import christmas.view.output.Printer;
import java.util.function.Supplier;

public class ChristmasInputView {
    private static final String REQUEST_DAY_MESSAGE
            = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_MENU_MESSAGE
            = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final Reader reader;
    private final Printer printer;
    private final InputValidator inputValidator;

    public ChristmasInputView(Reader reader, Printer printer, InputValidator inputValidator) {
        this.reader = reader;
        this.printer = printer;
        this.inputValidator = inputValidator;
    }

    public DayRequest receiveDateToVisit() {
        return retryInputOnException(() -> {
            printer.print(REQUEST_DAY_MESSAGE);
            String input = reader.readLine();
            inputValidator.validateNumberFormat(input);

            return new DayRequest(Integer.parseInt(input));
        });
    }

    public MenuAndCountRequest receiveMenuAndCount() {
        return retryInputOnException(() -> {
            printer.print(REQUEST_MENU_MESSAGE);
            String input = reader.readLine();
            inputValidator.validateMenuAndCount(input);

            return new MenuAndCountRequest(MenuNameCountMapper.map(input));
        });
    }

    private <T> T retryInputOnException(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            Printer.printException(e.getMessage());
            return retryInputOnException(supplier);
        }
    }
}
