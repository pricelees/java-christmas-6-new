package christmas.dto.request;

import christmas.constant.Date;
import christmas.constant.ErrorMessages;

public record DayRequest(int dayOfMonth) {
    public DayRequest {
        if (dayOfMonth < Date.EVENT_START_DAY_OF_MONTH || dayOfMonth > Date.EVENT_END_DAT_OF_MONTH) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_DAY_OF_MONTH.getMessage());
        }
    }
}
