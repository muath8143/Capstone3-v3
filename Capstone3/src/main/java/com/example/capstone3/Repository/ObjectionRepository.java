package com.example.capstone3.Repository;

import com.example.capstone3.Model.Objection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectionRepository extends JpaRepository<Objection, Integer> {

    Objection findObjectionById(Integer id);

}