package com.raise.repository;

import com.raise.domain.Allocation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Allocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
}
