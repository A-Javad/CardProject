package az.company.cardproject.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 4,max = 6)
    private String type;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
    @NotNull
    private Boolean hasCahback;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    @Min(value = 1)
    private Long cardId;
}
