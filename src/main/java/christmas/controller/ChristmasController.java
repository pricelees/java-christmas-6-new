package christmas.controller;

import christmas.domain.Customer;
import christmas.dto.response.AmountResponse;
import christmas.dto.response.BadgeResponse;
import christmas.dto.response.BenefitsResponses;
import christmas.dto.response.FreeMenuResponses;
import christmas.dto.response.OrderedMenuResponses;
import christmas.service.CustomerService;
import christmas.view.input.ChristmasInputView;
import christmas.view.output.ChristmasOutputView;

public class ChristmasController {
    private final CustomerService customerService;
    private final ChristmasInputView christmasInputView;
    private final ChristmasOutputView christmasOutputView;

    public ChristmasController(
            CustomerService customerService,
            ChristmasInputView christmasInputView,
            ChristmasOutputView christmasOutputView
    ) {
        this.customerService = customerService;
        this.christmasInputView = christmasInputView;
        this.christmasOutputView = christmasOutputView;
    }

    public void run() {
        Customer customer = welcomeCustomer();
        printAllInfo(customer);

    }

    private void printAllInfo(Customer customer) {
        christmasOutputView.printPreviewMessage(customerService.receiveDayInfo(customer));
        printOrderedMenu(customer);
        printAmountOfBeforeDiscount(customer);
        printFreeMenu(customer);
        printBenefitsDescription(customer);
        printTotalBenefitsAmount(customer);
        printAmountToPay(customer);
        printBadgeName(customer);
    }

    private void printOrderedMenu(Customer customer) {
        OrderedMenuResponses orderedMenuResponses = customerService.receiveAllOrderedMenuInfo(customer);
        christmasOutputView.printOrderedMenu(orderedMenuResponses);
    }

    private void printAmountOfBeforeDiscount(Customer customer) {
        AmountResponse amountResponse = customerService.receiveAllAmountInfo(customer);
        christmasOutputView.printAmountOfBeforeDiscount(amountResponse);
    }

    private void printFreeMenu(Customer customer) {
        FreeMenuResponses freeMenuResponses = customerService.receiveFreeMenuInfo(customer);
        christmasOutputView.printFreeMenu(freeMenuResponses);
    }

    private void printBenefitsDescription(Customer customer) {
        BenefitsResponses benefitsResponses = customerService.receiveAllBenefitsInfo(customer);
        christmasOutputView.printBenefitsDescription(benefitsResponses);
    }

    private void printTotalBenefitsAmount(Customer customer) {
        AmountResponse amountResponse = customerService.receiveAllAmountInfo(customer);
        christmasOutputView.printTotalBenefitsAmount(amountResponse);
    }

    private void printAmountToPay(Customer customer) {
        AmountResponse amountResponse = customerService.receiveAllAmountInfo(customer);
        christmasOutputView.printAmountToPay(amountResponse);
    }

    private void printBadgeName(Customer customer) {
        BadgeResponse badgeResponse = customerService.receiveBadgeInfo(customer);
        christmasOutputView.printBadgeName(badgeResponse);
    }


    private Customer welcomeCustomer() {
        christmasOutputView.printWelcomeMessage();

        return customerService.loadCustomerInfo(
                christmasInputView.receiveDateToVisit(),
                christmasInputView.receiveMenuAndCount()
        );
    }
}
