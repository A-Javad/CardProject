package az.company.cardproject.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pan;
    private Long customerId;
    private BigDecimal balance;
    private Timestamp createdAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List <Transaction> transactions;
}
