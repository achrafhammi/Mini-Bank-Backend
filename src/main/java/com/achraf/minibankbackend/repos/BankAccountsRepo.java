package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountsRepo extends JpaRepository<BankAccount, Long> {
    Boolean existsByPassCode(Integer passCode);
}
