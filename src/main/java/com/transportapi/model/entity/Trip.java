package com.transportapi.model.entity;

import java.time.LocalDateTime;

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
@Table(name = "trip")
public class Trip {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long tripId;
   private LocalDateTime dateAndHour;
   private int numberOfPassengers;
   private String reservationStatus;

   @ManyToOne
   @JoinColumn(name = "user_id")
   @Schema(hidden = true)
   private User user;

   
}
