package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Kitchen;
import com.example.capstone3.Model.Objection;
import com.example.capstone3.Model.Violation;
import com.example.capstone3.Repository.KitchenRepository;
import com.example.capstone3.Repository.ObjectionRepository;
import com.example.capstone3.Repository.ViolationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ObjectionService {

    private final ObjectionRepository objectionRepository;
    private final ViolationRepository violationRepository;
    private final KitchenRepository kitchenRepository;


    public List<Objection> getAll() {
        return objectionRepository.findAll();
    }

    public void addObjection(Integer violationId, Integer kitchenId, Objection objection) {
        Violation violation = violationRepository.findViolationById(violationId);
        Kitchen kitchen = kitchenRepository.findKitchenById(kitchenId);

        if (violation == null || kitchen == null) {
            throw new ApiException("Violation id or kitchen id is not exists");
        }

        if (violation.getKitchen() == null || !violation.getKitchen().getId().equals(kitchen.getId())) {
            throw new ApiException("This violation does not belong to this kitchen");
        }

        Set<Objection> violationObjections = violation.getObjections();
        if (violationObjections != null && !violationObjections.isEmpty()) {
            throw new ApiException("This violation already has an objection");
        }

        objection.setViolation(violation);
        objection.setKitchen(kitchen);
        objection.setStatus("OPEN");
        objectionRepository.save(objection);
    }

    public void updateObjection(Integer id, Objection objection) {
        Objection oldObjection = objectionRepository.findObjectionById(id);
        if (oldObjection == null) {
            throw new ApiException("Objection id not found");
        }

        oldObjection.setReason(objection.getReason());

        objectionRepository.save(oldObjection);
    }

    public void deleteObjection(Integer id) {
        Objection objection = objectionRepository.findObjectionById(id);
        if (objection == null) {
            throw new ApiException("Objection id not found");
        }

        objectionRepository.delete(objection);
    }

    public void approveObjection(Integer id, String response) {
        Objection objection = objectionRepository.findObjectionById(id);
        if (objection == null) {
            throw new ApiException("Objection id not found");
        }

        objection.setStatus("APPROVED");
        if (response != null && !response.isEmpty()) {
            objection.setResponse(response);
        }

        objectionRepository.save(objection);
    }

    public void rejectObjection(Integer id, String response) {
        Objection objection = objectionRepository.findObjectionById(id);
        if (objection == null) {
            throw new ApiException("Objection id not found");
        }

        objection.setStatus("REJECTED");
        if (response != null && !response.isEmpty()) {
            objection.setResponse(response);
        }
        objectionRepository.save(objection);
    }
}