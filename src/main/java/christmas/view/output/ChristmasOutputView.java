package christmas.view.output;

import christmas.dto.response.AmountResponse;
import christmas.dto.response.BadgeResponse;
import christmas.dto.response.BenefitsResponses;
import christmas.dto.response.DayResponse;
import christmas.dto.response.FreeMenuResponses;
import christmas.dto.response.OrderedMenuResponses;
import java.text.DecimalFormat;

public class ChristmasOutputView {

    private final Printer printer;

    public ChristmasOutputView(Printer printer) {
        this.printer = printer;
    }

    public void printWelcomeMessage() {
        printer.print(Constants.WELCOME_MESSAGE);
    }

    public void printPreviewMessage(DayResponse dayResponse) {
        printer.printFormat(Constants.PREVIEW_FORMAT, dayResponse.dayOfMonth());
        printer.printEmptyLine();
    }

    public void printOrderedMenu(OrderedMenuResponses orderedMenuResponses) {
        printer.print(Constants.ORDERED_MENU_PREFIX);
        orderedMenuResponses.orderedMenuResponses().forEach(orderedMenuResponse ->
                printer.printFormat(
                        Constants.MENU_FORMAT,
                        orderedMenuResponse.name(),
                        NumberFormatter.format(orderedMenuResponse.count())
                )
        );
        printer.printEmptyLine();
    }

    public void printAmountOfBeforeDiscount(AmountResponse amountResponse) {
        printer.print(Constants.AMOUNT_ORDERED_MENU_PREFIX);
        printer.printFormat(Constants.AMOUNT_FORMAT, NumberFormatter.format(
                amountResponse.amountOfOrderedMenu()
        ));
        printer.printEmptyLine();
    }

    public void printFreeMenu(FreeMenuResponses freeMenuResponses) {
        printer.print(Constants.FREE_MENU_PREFIX);
        if (freeMenuResponses.allFreeMenu().isEmpty()) {
            printer.print("없음");
        }
        freeMenuResponses.allFreeMenu().forEach(freeMenuResponse ->
                printer.printFormat(
                        Constants.MENU_FORMAT,
                        freeMenuResponse.name(),
                        NumberFormatter.format(freeMenuResponse.count())
                )
        );
        printer.printEmptyLine();
    }

    public void printBenefitsDescription(BenefitsResponses benefitsResponses) {
        printer.print(Constants.BENEFITS_PREFIX);
        if (printedNone(benefitsResponses)) {
            return;
        }
        benefitsResponses.benefits().forEach(benefitsResponse ->
                printer.printFormat(
                        Constants.BENEFIT_FORMAT,
                        benefitsResponse.name(),
                        NumberFormatter.format(benefitsResponse.amount())
                )
        );
        printer.printEmptyLine();
    }

    private boolean printedNone(BenefitsResponses benefitsResponses) {
        boolean cantApplicable = benefitsResponses.benefits().stream()
                .allMatch(benefitsResponse -> benefitsResponse.amount() == Constants.ZERO);
        if (cantApplicable) {
            printer.print(Constants.NONE);
            printer.printEmptyLine();
            return true;
        }
        return false;
    }

    public void printTotalBenefitsAmount(AmountResponse amountResponse) {
        printer.print(Constants.BENEFITS_AMOUNT_PREFIX);
        int amount = amountResponse.amountOfTotalBenefits();
        String format = "";
        if (amount == Constants.ZERO) {
            format = Constants.AMOUNT_FORMAT;
        }
        if (amount > Constants.ZERO) {
            format = Constants.BENEFIT_AMOUNT_FORMAT;
        }
        printer.printFormat(format, NumberFormatter.format(amount));
        printer.printEmptyLine();
    }

    public void printAmountToPay(AmountResponse amountResponse) {
        printer.print(Constants.AFTER_DISCOUNT_AMOUNT_PREFIX);
        printer.printFormat(Constants.AMOUNT_FORMAT, NumberFormatter.format(
                amountResponse.amountOfAfterDiscounts()
        ));
        printer.printEmptyLine();
    }

    public void printBadgeName(BadgeResponse badgeResponse) {
        printer.print(Constants.EVENT_BADGE_PREFIX);
        printer.print(badgeResponse.name());
    }

    private static class NumberFormatter {
        private static final DecimalFormat formatter = new DecimalFormat("###,###");

        private NumberFormatter() {
        }

        static String format(int input) {
            return formatter.format(input);
        }
    }

    private static class Constants {
        private Constants() {
        }
        private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
        private static final String PREVIEW_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
        private static final String ORDERED_MENU_PREFIX = "<주문 메뉴>";
        private static final String AMOUNT_ORDERED_MENU_PREFIX = "<할인 전 총주문 금액>";
        private static final String FREE_MENU_PREFIX = "<증정 메뉴>";
        private static final String BENEFITS_PREFIX = "<혜택 내역>";
        private static final String BENEFITS_AMOUNT_PREFIX = "<총혜택 금액>";
        private static final String AFTER_DISCOUNT_AMOUNT_PREFIX = "<할인 후 예상 결제 금액>";
        private static final String EVENT_BADGE_PREFIX = "<12월 이벤트 배지>";
        private static final String MENU_FORMAT = "%s %s개";
        private static final String BENEFIT_FORMAT = "%s: -%s원";
        private static final String BENEFIT_AMOUNT_FORMAT = "-%s원";
        private static final String AMOUNT_FORMAT = "%s원";
        private static final String NONE = "없음";
        private static final int ZERO = 0;
    }
}
