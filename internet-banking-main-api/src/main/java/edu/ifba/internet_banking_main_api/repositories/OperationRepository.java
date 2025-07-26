package edu.ifba.internet_banking_main_api.repositories;

import edu.ifba.internet_banking_main_api.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
}