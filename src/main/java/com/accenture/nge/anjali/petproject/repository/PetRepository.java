package com.accenture.nge.anjali.petproject.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.nge.anjali.petproject.entity.PetInfo;

@Repository
@EnableScan
public interface PetRepository extends CrudRepository<PetInfo, String> {
    
}
