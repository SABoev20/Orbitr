package com.backend.orbitr.dal.repositories;

import com.backend.orbitr.dal.models.Technology;
import com.backend.orbitr.dal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer>{
}
