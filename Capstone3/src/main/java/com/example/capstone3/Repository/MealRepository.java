package com.example.capstone3.Repository;

import com.example.capstone3.Model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
    Meal findMealById(Integer id);
}
