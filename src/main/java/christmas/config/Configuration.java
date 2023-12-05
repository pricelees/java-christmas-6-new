package christmas.config;

import christmas.controller.ChristmasController;
import christmas.service.CustomerService;
import christmas.service.badge.BadgeService;
import christmas.service.discount.DiscountService;
import christmas.service.freemenu.FreeMenuService;
import christmas.service.menu.MenuService;
import christmas.view.input.ChristmasInputView;
import christmas.view.input.InputValidator;
import christmas.view.input.Reader;
import christmas.view.output.ChristmasOutputView;
import christmas.view.output.Printer;

public class Configuration {
    private Configuration() {
    }

    public static ChristmasController christmasController() {
        return new ChristmasController(
                customerService(),
                christmasInputView(),
                christmasOutputView()
        );
    }

    private static CustomerService customerService() {
        return new CustomerService(
                menuService(),
                discountService(),
                freeMenuService(),
                badgeService()
        );
    }

    private static ChristmasInputView christmasInputView() {
        return new ChristmasInputView(reader(), printer(), inputValidator());
    }

    private static ChristmasOutputView christmasOutputView() {
        return new ChristmasOutputView(printer());
    }

    private static MenuService menuService() {
        return new MenuService();
    }

    private static DiscountService discountService() {
        return new DiscountService();
    }

    private static FreeMenuService freeMenuService() {
        return new FreeMenuService();
    }

    private static BadgeService badgeService() {
        return new BadgeService();
    }

    private static Reader reader() {
        return new Reader();
    }

    private static Printer printer() {
        return new Printer();
    }

    private static InputValidator inputValidator() {
        return new InputValidator();
    }
}
