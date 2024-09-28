package by.mikhalachkin.repository;

import by.mikhalachkin.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
  List<Skill> findByPositionId(Long positionId);
}