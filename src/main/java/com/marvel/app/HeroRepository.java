package com.marvel.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(path = "hero")
@CrossOrigin(origins = "http://localhost:4200")
public interface HeroRepository extends JpaRepository<Hero, Long> {
}
