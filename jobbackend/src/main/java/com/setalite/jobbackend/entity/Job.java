package com.setalite.jobbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@Builder
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor(force = true)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    String type;
    String description;
    String location;
    String salary;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Company company;
}
