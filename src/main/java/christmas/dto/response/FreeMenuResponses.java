package christmas.dto.response;

import java.util.List;

public record FreeMenuResponses(List<FreeMenuResponse> allFreeMenu) {
    public record FreeMenuResponse(String name, int count, int amount) {

    }
}
