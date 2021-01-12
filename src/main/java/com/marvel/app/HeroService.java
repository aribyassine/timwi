package com.marvel.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroService {
    private final ApiService apiService;

    public HeroService(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<Hero> fetchHeroes() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiService.getHeroesApiUrl(), String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Hero> heroes = new ArrayList<>();
        try {
            JsonNode root = mapper.readTree(response.getBody()).at("/data/results");
            root.forEach(
                    node -> {
                        Hero build = Hero.builder()
                                .name(node.get("name").asText())
                                .description(node.get("description").asText())
                                .thumbnail(node.at("/thumbnail/path").asText() + "." + node.at("/thumbnail/extension").asText())
                                .inTeam(false)
                                .build();
                        heroes.add(build);
                    }
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}
