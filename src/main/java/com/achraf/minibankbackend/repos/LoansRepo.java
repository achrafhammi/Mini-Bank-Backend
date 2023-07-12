package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepo extends JpaRepository<Loan, Long> {
}
