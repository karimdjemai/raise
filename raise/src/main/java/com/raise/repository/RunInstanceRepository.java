package com.raise.repository;

import com.raise.domain.RunInstance;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RunInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RunInstanceRepository extends JpaRepository<RunInstance, Long> {
}
