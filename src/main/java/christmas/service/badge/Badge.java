package christmas.service.badge;

import java.util.function.Predicate;

public enum Badge {
    SANTA_BADGE("산타", benefitsAmount -> benefitsAmount >= 20_000),
    TREE_BADGE("트리", benefitsAmount -> benefitsAmount >= 10_000 && benefitsAmount < 20_000),
    STAR_BADGE("별", benefitsAmount -> benefitsAmount >= 5_000 && benefitsAmount < 10_000),
    NONE("없음", benefitsAmount -> benefitsAmount < 5000);

    private final String name;
    private final Predicate<Integer> condition;

    Badge(String name, Predicate<Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    public Boolean isApplicable(int benefitsAmount) {
        return condition.test(benefitsAmount);
    }

    public String getName() {
        return name;
    }
}
