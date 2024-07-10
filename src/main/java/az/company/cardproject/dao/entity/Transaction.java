package az.company.cardproject.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String type;
    private BigDecimal amount;
    private Boolean hasCashback;
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
