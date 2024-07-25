package az.company.cardproject.specification;

import az.company.cardproject.dao.entity.Card;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CardSpecification {
    public static Specification<Card>hasCustomerId(String customerId){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"),customerId));

    }
    public static Specification<Card>hasBalanceGreaterThan(BigDecimal minBalance){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("balance"),minBalance));
    }
    public static Specification<Card>hasBalanceLessThan(BigDecimal maxBalance){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("balance"),maxBalance));
    }
}
