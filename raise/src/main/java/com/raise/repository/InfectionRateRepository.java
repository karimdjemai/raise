package com.raise.repository;

import com.raise.domain.InfectionRate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InfectionRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfectionRateRepository extends JpaRepository<InfectionRate, Long> {
}
