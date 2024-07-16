package az.company.cardproject.model.dto;

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
    private Long id;
    private String type;
    private BigDecimal amount;
    private Boolean hasCahback;
    private LocalDateTime createdAt;
    private Long cardId;
}
