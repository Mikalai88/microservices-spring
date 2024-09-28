package by.mikhalachkin.controller;

import by.mikhalachkin.dto.PositionDto;
import by.mikhalachkin.dto.SkillDto;
import by.mikhalachkin.dto.SkillResponseDto;
import by.mikhalachkin.model.Skill;
import by.mikhalachkin.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/skills")
@AllArgsConstructor
public class SkillController {

  private final SkillService skillService;
  private final RestTemplate restTemplate;

  @GetMapping("/{positionId}")
  public ResponseEntity<SkillResponseDto> getSkills(@PathVariable Long positionId) {
    return ResponseEntity.ok(skillService.getSkills(positionId));
  }

  @PostMapping
  public SkillDto createSkill(@RequestBody SkillDto skillDto) {
    String positionServiceUrl = "http://POSITION-SERVICE/positions/" + skillDto.positionId();
    ResponseEntity<PositionDto> response = restTemplate.getForEntity(positionServiceUrl, PositionDto.class, skillDto.positionId());

    if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Position not found");
    }

    Skill skill = skillService.createSkill(skillDto);
    return new SkillDto(skill.getId(), skill.getSkillName(), skill.getPositionId());
  }
}