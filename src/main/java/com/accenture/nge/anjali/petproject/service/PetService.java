package com.accenture.nge.anjali.petproject.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.nge.anjali.petproject.entity.PetInfo;
import com.accenture.nge.anjali.petproject.model.Pet;
import com.accenture.nge.anjali.petproject.repository.PetRepository;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;

    public Pet getPetById(String id) {
        PetInfo petInfo = petRepository.findById(id).get();

        return Pet.builder()
            .id(petInfo.getId())
            .name(petInfo.getName())
            .species(petInfo.getSpecies())
            .build();
    }

    public List<Pet> getAllPets() {
        Iterable<PetInfo> pets = petRepository.findAll();

        return StreamSupport.stream(pets.spliterator(), false)
            .map(petInfo -> Pet.builder()
                .id(petInfo.getId())
                .name(petInfo.getName())
                .species(petInfo.getSpecies())
                .build())
            .toList();
    }

    public Pet createPet(Pet pet) {
        PetInfo petInfo = petRepository.save(pet.toPetInfo());

        return Pet.builder()
            .id(petInfo.getId())
            .name(petInfo.getName())
            .species(petInfo.getSpecies())
            .build();
    }
}
