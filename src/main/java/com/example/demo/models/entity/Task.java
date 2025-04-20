package com.example.demo.models.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
   private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
   private String name;
   private String description;
   private Boolean completed;

   private Long userId;

   @Column(columnDefinition="timestamp default (sysdate())")
   private final Timestamp createdAt = new Timestamp(System.currentTimeMillis());

   @Column(columnDefinition="timestamp default (sysdate())") 
   private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

   public void setUpdatedAtDefault() {
    this.updatedAt = new Timestamp(System.currentTimeMillis());
   }
}
