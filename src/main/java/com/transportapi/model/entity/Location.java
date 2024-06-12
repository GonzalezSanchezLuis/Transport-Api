package com.transportapi.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private double distance;
    private int estimatedDuration;

    private String originName;
    private double originLatitude;
    private double originLongitude;
    private String destinationName;
    private double destinationLatitude;
    private double destinationLongitude;

    @ManyToOne
    @Schema(hidden = true)
    @JoinColumn(name = "user_id")
    private User user;
}
