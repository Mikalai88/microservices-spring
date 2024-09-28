package by.mikhalachkin.service;

import by.mikhalachkin.dto.PositionDto;
import by.mikhalachkin.model.Position;
import by.mikhalachkin.repository.PositionRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PositionService {
  @Autowired
  private PositionRepository positionRepository;

  public PositionDto getPosition(Long id) {
    return positionRepository.findById(id)
            .map(position -> new PositionDto(position.getId(), position.getPositionName()))
            .orElse(null);
  }

  public List<Position> getAllPositions() {
    return positionRepository.findAll();
  }

  public Position createPosition(Position position) {
    return positionRepository.save(position);
  }
}