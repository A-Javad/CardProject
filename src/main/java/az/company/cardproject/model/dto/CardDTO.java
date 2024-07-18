package az.company.cardproject.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 16,max=16)
    private String pan;
    @NotNull
    @Min(1)
    private Long customerId;
    @NotNull
    @DecimalMin("0.001")
    private BigDecimal balance;
}
