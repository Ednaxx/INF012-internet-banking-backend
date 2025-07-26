package edu.ifba.internet_banking_main_api.repositories;

import edu.ifba.internet_banking_main_api.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByNumberAndBranch(String number, String branch);
}