package com.achraf.minibankbackend.repos;

import com.achraf.minibankbackend.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Long> {
}
