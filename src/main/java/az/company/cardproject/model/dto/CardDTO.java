package az.company.cardproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String pan;
    private Long customerId;
    private BigDecimal balance;
}
