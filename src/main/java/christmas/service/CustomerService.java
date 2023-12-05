package christmas.service;

import christmas.domain.Customer;
import christmas.dto.request.DayRequest;
import christmas.dto.request.MenuAndCountRequest;
import christmas.dto.response.AmountResponse;
import christmas.dto.response.BadgeResponse;
import christmas.dto.response.BenefitsResponses;
import christmas.dto.response.BenefitsResponses.BenefitsResponse;
import christmas.dto.response.DayResponse;
import christmas.dto.response.FreeMenuResponses;
import christmas.dto.response.OrderedMenuResponses;
import christmas.service.badge.BadgeService;
import christmas.service.discount.DiscountService;
import christmas.service.freemenu.FreeMenuService;
import christmas.service.menu.MenuService;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static final String FREE_EVENT = "증정 이벤트";
    private final MenuService menuService;
    private final DiscountService discountService;
    private final FreeMenuService freeMenuService;
    private final BadgeService badgeService;

    public CustomerService(
            MenuService menuService,
            DiscountService discountService,
            FreeMenuService freeMenuService,
            BadgeService badgeService
    ) {
        this.menuService = menuService;
        this.discountService = discountService;
        this.freeMenuService = freeMenuService;
        this.badgeService = badgeService;
    }

    public Customer loadCustomerInfo(DayRequest dayRequest, MenuAndCountRequest menuAndCountRequest) {
        return Customer.valueOf(dayRequest.dayOfMonth(), menuAndCountRequest.menuAndCount());
    }

    public DayResponse receiveDayInfo(Customer customer) {
        return new DayResponse(customer.getDayOfMonth());
    }

    public OrderedMenuResponses receiveAllOrderedMenuInfo(Customer customer) {
        return new OrderedMenuResponses(menuService.receiveOrderedMenuInfo(customer));
    }

    public AmountResponse receiveAllAmountInfo(Customer customer) {
        int amountOfOrderedMenu = menuService.calculateOrderedMenuAmount(customer);
        int amountOfDiscounts = discountService.calculateTotalDiscountAmount(customer);
        int amountOfAfterDiscounts = amountOfOrderedMenu - amountOfDiscounts;
        int amountOfTotalBenefits = calculateTotalBenefitsAmount(customer);

        return new AmountResponse(amountOfOrderedMenu, amountOfAfterDiscounts, amountOfTotalBenefits);
    }

    public FreeMenuResponses receiveFreeMenuInfo(Customer customer) {
        int amountOfOrderedMenu = menuService.calculateOrderedMenuAmount(customer);
        return new FreeMenuResponses(freeMenuService.receiveAllFreeMenu(amountOfOrderedMenu));
    }

    public BenefitsResponses receiveAllBenefitsInfo(Customer customer) {
        List<BenefitsResponse> benefitsFromDiscount = new ArrayList<>(discountService.receiveDiscountInfo(customer));
        int totalFreeMenuPrice = freeMenuService.calculateTotalFreeMenuPrice(
                menuService.calculateOrderedMenuAmount(customer)
        );
        benefitsFromDiscount.add(new BenefitsResponse(FREE_EVENT, totalFreeMenuPrice));

        return new BenefitsResponses(benefitsFromDiscount);
    }

    public BadgeResponse receiveBadgeInfo(Customer customer) {
        int amountOfTotalBenefits = calculateTotalBenefitsAmount(customer);

        return new BadgeResponse(badgeService.receiveBadgeInfo(amountOfTotalBenefits));
    }

    private int calculateTotalBenefitsAmount(Customer customer) {
        int amountOfOrderedMenu = menuService.calculateOrderedMenuAmount(customer);
        int amountOfDiscounts = discountService.calculateTotalDiscountAmount(customer);

        return freeMenuService.calculateTotalFreeMenuPrice(amountOfOrderedMenu) + amountOfDiscounts;
    }
}
