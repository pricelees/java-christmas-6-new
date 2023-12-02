package christmas.constant;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;

public class Date {
    private Date() {
    }

    public static final int YEAR = 2023;
    public static final Month MONTH = Month.DECEMBER;
    public static final int EVENT_START_DAY_OF_MONTH = 1;
    public static final int EVENT_END_DAT_OF_MONTH = 31;
    public static final int CHRISTMAS = 25;
    public static final List<Integer> SPECIAL_DAYS = List.of(
            3, 10, 17, 24, 25, 31
    );
    public static final List<DayOfWeek> WEEKDAYS = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY
    );
    public static final List<DayOfWeek> WEEKENDS = List.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
    );
}
