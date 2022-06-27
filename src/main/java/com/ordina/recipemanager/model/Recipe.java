package com.ordina.recipemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @NonNull @Size(min = 2, max = 255)
    private String title;
    private boolean isVegetarian;
    private int noOfServing;
    @Size(min = 10, max = 3000)
    private String instructions;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ToString.Exclude
    private List<Ingredient> ingredients;
}
