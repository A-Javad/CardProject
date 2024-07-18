package az.company.cardproject.dao.repository;

import az.company.cardproject.dao.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT t FROM Transaction t WHERE DATE(t.createdAt) = :transactionDate")
    List<Transaction>findByTransactionDate(@Param("transactionDate") LocalDate transactionDate);
}
