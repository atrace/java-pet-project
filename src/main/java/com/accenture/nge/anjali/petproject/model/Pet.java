package com.accenture.nge.anjali.petproject.model;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.lang.Nullable;

import com.accenture.nge.anjali.petproject.entity.PetInfo;
import com.accenture.nge.anjali.petproject.entity.Species;

import lombok.Builder;
import lombok.Data;
 
@Data
@Builder(toBuilder = true)
public class Pet {
    @Nullable
    private final String id;
    
    private final String name;
    private final Species species;

    @EnableScan
    public PetInfo toPetInfo() {
        return PetInfo.builder()
            .id(id)
            .name(name)
            .species(species)
            .build();
    }
}
