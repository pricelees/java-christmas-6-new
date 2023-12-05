package christmas.dto.response;

import java.util.List;

public record OrderedMenuResponses(List<OrderedMenuResponse> orderedMenuResponses) {
    public record OrderedMenuResponse(String name, int count) {
    }
}
