package by.mikhalachkin.client;

import by.mikhalachkin.dto.PositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PositionClient {

  @Autowired
  private RestTemplate restTemplate;

  public PositionDto getPosition(Long positionId) {
    String positionServiceUrl = "http://POSITION-SERVICE/positions/" + positionId;
    return restTemplate.getForObject(positionServiceUrl, PositionDto.class);
  }
}