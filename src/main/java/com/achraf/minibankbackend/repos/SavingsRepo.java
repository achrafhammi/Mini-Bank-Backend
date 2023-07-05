package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepo extends JpaRepository<Savings, Long> {
}
