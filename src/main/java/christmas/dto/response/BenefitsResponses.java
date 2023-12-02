package christmas.dto.response;

import java.util.List;

public record BenefitsResponses(List<BenefitsResponse> benefits) {
    public record BenefitsResponse(String name, int amount) {
    }
}
