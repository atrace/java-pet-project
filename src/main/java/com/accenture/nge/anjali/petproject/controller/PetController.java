package com.accenture.nge.anjali.petproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.nge.anjali.petproject.model.Pet;
import com.accenture.nge.anjali.petproject.service.PetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pet getPet(@PathVariable String id) {
        return petService.getPetById(id);
    };

    @GetMapping
    public String[] getPets() {
        String[] foo = {"hi", "hello"};
        return foo;
    }

    @PostMapping
    public Pet addPet(@Valid @RequestBody Pet pet) {
        Pet newPet = petService.createPet(pet);
        return newPet;
    }
}
