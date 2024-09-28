package by.mikhalachkin.repository;

import by.mikhalachkin.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}