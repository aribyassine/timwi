package com.marvel.app;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hero {
    @Id
    @GeneratedValue
    private Long id;
    private @NonNull String name;
    @Lob
    private String description;
    private String thumbnail;
    private Boolean inTeam;
}
