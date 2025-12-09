package com.example.capstone3.Repository;

import com.example.capstone3.Model.Campaign;
import com.example.capstone3.Model.Kitchen;
import com.example.capstone3.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findRatingById(Integer id);

    List<Rating> findRatingByKitchen(Kitchen kitchen);
    boolean existsByCampaignAndKitchen(Campaign campaign, Kitchen kitchen);

}
