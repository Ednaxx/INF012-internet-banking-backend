package edu.ifba.internet_banking_main_api.repositories;

import edu.ifba.internet_banking_main_api.models.Operation;
import edu.ifba.internet_banking_main_api.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    List<Operation> findByAccountOrderByCreatedAtDesc(Account account);
}
