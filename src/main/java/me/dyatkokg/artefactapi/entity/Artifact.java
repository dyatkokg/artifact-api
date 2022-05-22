package me.dyatkokg.artefactapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime created;

    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;

    private String category;

    private String description;

    private byte[] artefact;
}