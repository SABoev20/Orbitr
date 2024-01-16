package com.backend.orbitr.dal.repositories;

import com.backend.orbitr.dal.models.Spaceship;
import com.backend.orbitr.dal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer>{
    List<Spaceship> findByUser(User user);
}
