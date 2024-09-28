package by.mikhalachkin.service;

import by.mikhalachkin.client.PositionClient;
import by.mikhalachkin.dto.PositionDto;
import by.mikhalachkin.dto.SkillDto;
import by.mikhalachkin.dto.SkillResponseDto;
import by.mikhalachkin.model.Skill;
import by.mikhalachkin.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService {

  private final SkillRepository skillRepository;
  private final PositionClient positionClient;

  public SkillResponseDto getSkills(Long positionId) {
    PositionDto position = positionClient.getPosition(positionId);

    if (position == null) {
      throw new EntityNotFoundException("Position not found.");
    }

    List<Skill> skills = skillRepository.findByPositionId(positionId);

    List<SkillDto> skillsDtos = skills.stream()
            .map(skill -> new SkillDto(skill.getId(), skill.getSkillName(), skill.getPositionId()))
            .collect(Collectors.toList());

    return new SkillResponseDto(skillsDtos);
  }

  public Skill createSkill(SkillDto skillDto) {
    Skill skill = new Skill();
    skill.setSkillName(skillDto.skillName());
    skill.setPositionId(skillDto.positionId());

    return skillRepository.save(skill);
  }
}

