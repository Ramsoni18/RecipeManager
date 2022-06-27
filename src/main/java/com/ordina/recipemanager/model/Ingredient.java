package com.ordina.recipemanager.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;

    public Ingredient(String baking_powder) {
    }
}
