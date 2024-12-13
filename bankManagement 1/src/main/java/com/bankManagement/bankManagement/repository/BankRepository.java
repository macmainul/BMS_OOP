package com.bankManagement.bankManagement.repository;

import com.bankManagement.bankManagement.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
   

    @Query("SELECT SUM(b.amount) FROM Bank b WHERE b.user.id = :userId AND b.status = 'Accepted'")
    Optional<Double> sumAcceptedAmountByUserId(Long userId);

    Optional<Bank> findByUserId(Long userId);
}
