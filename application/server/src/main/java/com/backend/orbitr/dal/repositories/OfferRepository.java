package com.backend.orbitr.dal.repositories;

import com.backend.orbitr.dal.models.Offer;
import com.backend.orbitr.dal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findDistinctByUser(User user);
}
