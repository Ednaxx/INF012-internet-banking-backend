package edu.ifba.internet_banking_main_api.repositories;

import edu.ifba.internet_banking_main_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}