package by.mikhalachkin.controller;

import by.mikhalachkin.dto.PositionDto;
import by.mikhalachkin.model.Position;
import by.mikhalachkin.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/positions")
public class PositionController {
  @Autowired
  private PositionService positionService;

  @Autowired
  public PositionController(PositionService positionService) {
    this.positionService = positionService;
  }

  @GetMapping
  public List<PositionDto> getAllPositions() {
    List<Position> positions = positionService.getAllPositions();
    return positions.stream()
            .map(position -> new PositionDto(position.getId(), position.getPositionName()))
            .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PositionDto> getPosition(@PathVariable Long id) {
    PositionDto position = positionService.getPosition(id);
    return position != null ? ResponseEntity.ok(position) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public PositionDto createPosition(@RequestBody PositionDto positionDto) {
    Position position = new Position();
    position.setPositionName(positionDto.positionName());

    Position savedPosition = positionService.createPosition(position);

    return new PositionDto(savedPosition.getId(), savedPosition.getPositionName());
  }
}