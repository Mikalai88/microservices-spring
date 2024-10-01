package by.mikhalachkin.repository;

import by.mikhalachkin.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
  Optional<UserCredential> findByUsername(String username);
}
