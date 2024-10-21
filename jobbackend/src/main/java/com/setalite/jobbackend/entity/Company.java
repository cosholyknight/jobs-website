package com.setalite.jobbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.ImageProducer;
import java.util.List;

@Getter
@Setter
@Builder
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor(force = true)
@JsonIgnoreProperties("jobs")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true, nullable = false)
    String name;

    String description;
    String officeAddress;

    @Column(unique = true)
    String contactEmail;

    @Column(unique = true)
    String contactPhone;

    @OneToMany(mappedBy="company", orphanRemoval = true)
    List<Job> jobs;

    String logoPath;
}
