package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepo extends JpaRepository<Loans, Long> {
}
